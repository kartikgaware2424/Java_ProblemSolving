package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobListing;
import org.example.Util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryRangeQueryDaoImpl implements SalaryRangeQueryDao {
Connection connection;
PreparedStatement pst;
    @Override
    public List<JobListing> searchJobsBySalaryRange(double minSalary, double maxSalary) throws SQLException, DatabaseConnectionException {
        List<JobListing> jobListings = new ArrayList<>();

        try {
            connection = ConnectionHelper.getConnection();

            String cmd = "select j.*, c.CompanyName from Jobs j join Companies c ON j.CompanyID = c.CompanyID where j.Salary between ? and ? order BY j.salary DESC";

            pst = connection.prepareStatement(cmd);
            pst.setDouble(1, minSalary);
            pst.setDouble(2, maxSalary);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                JobListing job = new JobListing();
                job.setJobID(rs.getInt("JobID"));
                job.setCompanyID(rs.getInt("CompanyID"));
                job.setJobTitle(rs.getString("JobTitle"));
                job.setJobDescription(rs.getString("JobDescription"));
                job.setJobLocation(rs.getString("JobLocation"));
                job.setSalary(rs.getDouble("Salary"));
                job.setJobType(rs.getString("JobType"));
                job.setPostedDate(rs.getDate("PostedDate"));
                job.setApplicationDeadline(rs.getDate("ApplicationDeadline"));
                job.setCompanyName(rs.getString("CompanyName"));

                jobListings.add(job);
            }

            return jobListings;

        } catch (SQLException e) {
            throw new SQLException("Error while searching jobs by salary range: " + e.getMessage());
        }
    }
}
