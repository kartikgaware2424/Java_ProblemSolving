package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobListing;
import org.example.Util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JobPostingDaoImpl implements JobPostingDao{
     Connection connection;
     PreparedStatement pst;
    @Override
    public String postJob(JobListing jobListing) throws SQLException, DatabaseConnectionException {
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "insert into Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType, PostedDate, ApplicationDeadline) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pst = connection.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, jobListing.getCompanyID());
            pst.setString(2, jobListing.getJobTitle());
            pst.setString(3, jobListing.getJobDescription());
            pst.setString(4, jobListing.getJobLocation());
            pst.setDouble(5, jobListing.getSalary());
            pst.setString(6, jobListing.getJobType());
            pst.setDate(7, new java.sql.Date(jobListing.getPostedDate().getTime()));
            pst.setDate(8, new java.sql.Date(jobListing.getApplicationDeadline().getTime()));
            pst.executeUpdate();

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {

                var generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int jobID = generatedKeys.getInt(1);
                    return "Job posted successfully! Job ID: " + jobID;
                }
            }
            return "Failed to post job.";

        } catch (SQLException e) {
            throw new SQLException("Error while posting job: " + e.getMessage());
        }
    }
}
