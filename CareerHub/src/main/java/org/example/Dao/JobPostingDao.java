package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobListing;

import java.sql.SQLException;

public interface JobPostingDao {
    String postJob(JobListing jobListing) throws SQLException, DatabaseConnectionException;
}
