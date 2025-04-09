package org.example.Dao;

import org.example.Exceptions.ApplicationDeadlineHandling;
import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobApplication;

import java.sql.SQLException;

public interface JobApplicationDao {
    String submitJobApplication(JobApplication application) throws SQLException, DatabaseConnectionException, ApplicationDeadlineHandling;
}
