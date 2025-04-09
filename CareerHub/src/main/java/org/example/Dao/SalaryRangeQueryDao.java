package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobListing;

import java.sql.SQLException;
import java.util.List;

public interface SalaryRangeQueryDao {
    List<JobListing> searchJobsBySalaryRange(double minSalary, double maxSalary) throws SQLException, DatabaseConnectionException;
}
