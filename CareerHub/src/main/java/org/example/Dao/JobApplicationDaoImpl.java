package org.example.Dao;

import org.example.Exceptions.ApplicationDeadlineHandling;
import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobApplication;
import org.example.Util.ConnectionHelper;

import java.sql.*;

public class JobApplicationDaoImpl implements JobApplicationDao{
    Connection connection;
    PreparedStatement pst;



    @Override
    public String submitJobApplication(JobApplication application) throws SQLException, DatabaseConnectionException, ApplicationDeadlineHandling {
        String result = null;
        try {

            checkApplicationDeadline(application.getJobID());
            connection = ConnectionHelper.getConnection();
            String cmd = "insert into Applications (jobID, applicantID, applicationDate, coverLetter) values (?, ?, ?, ?)";
            pst = connection.prepareStatement(cmd,Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, application.getJobID());
            pst.setInt(2, application.getApplicantID());
            pst.setDate(3, new java.sql.Date(application.getApplicationDate().getTime()));
            pst.setString(4, application.getCoverLetter());
            pst.executeUpdate();


            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int applicationID = generatedKeys.getInt(1);
                    result = "Application submitted successfully! Application ID: " + applicationID;
                }
            } else {
                result = "Failed to submit application.";
            }
        } catch (SQLException e) {
            throw new SQLException("Error while submitting job application: " + e.getMessage());
        }
        return result;
    }


    private void checkApplicationDeadline(int jobID) throws SQLException, ApplicationDeadlineHandling, DatabaseConnectionException {

            connection = ConnectionHelper.getConnection();
            String query = "select applicationDeadline FROM jobs WHERE jobID = ?";
            pst = connection.prepareStatement(query);
            pst.setInt(1, jobID);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Date deadline = rs.getDate("applicationDeadline");
                Date currentDate = new java.sql.Date(System.currentTimeMillis());


                if (currentDate.after(deadline)) {
                    throw new ApplicationDeadlineHandling("Application deadline has passed. Applications are no longer being accepted for this job.");
                }

    }
}}
