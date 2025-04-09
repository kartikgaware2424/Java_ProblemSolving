package org.example.Dao;

import org.example.Exceptions.InvalidEmailException;
import org.example.Model.Applicant;

import java.sql.SQLException;

public interface ApplicantDao {
    String createApplicantProfile(Applicant applicant) throws SQLException, InvalidEmailException;
}
