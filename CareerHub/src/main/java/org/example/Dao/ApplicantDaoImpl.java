package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Exceptions.InvalidEmailException;
import org.example.Model.Applicant;
import org.example.Util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicantDaoImpl implements ApplicantDao{
    Connection connection;
    PreparedStatement pst;
    @Override
    public String createApplicantProfile(Applicant applicant) throws SQLException, InvalidEmailException {
        try {

            validateEmail(applicant.getEmail());
            connection = ConnectionHelper.getConnection();
            String cmd = "insert into Applicants (FirstName,LastName,Email,Phone,Resume) " +
                    "values ( ?, ?, ?, ?, ?)";
            pst = connection.prepareStatement(cmd);

            pst.setString(1,applicant.getFirstName());
            pst.setString(2,applicant.getLastName());
            pst.setString(3,applicant.getEmail());
            pst.setString(4,applicant.getPhone());
            pst.setString(5,applicant.getResume());
            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (DatabaseConnectionException | InvalidEmailException e) {
             throw new InvalidEmailException("Wrong Email Format");
        }
        return "Applicant Added SuccessFully.....";

    }
    private void validateEmail(String email) throws InvalidEmailException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidEmailException("Invalid email format : " + email);
        }
    }
}
