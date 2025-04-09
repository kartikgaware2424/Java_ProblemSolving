package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobApplication;
import org.example.Model.JobListing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JobPostingDaoTest {
    private JobPostingDaoImpl jobposting;
    private JobListing jobApplication;

    @BeforeEach
    void setUp() {
        jobposting = new JobPostingDaoImpl();
        jobApplication=new JobListing();
    }

    @Test
    void Testjobposting() throws DatabaseConnectionException, SQLException {
        jobApplication.setCompanyID(1);
        jobApplication.setJobTitle("Test Job - " +System.currentTimeMillis());
        jobApplication.setJobDescription("Job description for testing.");
        jobApplication.setJobLocation("Pune");
        jobApplication.setSalary(85000.0);
        jobApplication.setJobType("Full-time");

        Date postedDate = new Date();
        Date deadline = new Date(System.currentTimeMillis() + 3*24 *60 * 60 * 1000); // +3 days

        jobApplication.setPostedDate(postedDate);
        jobApplication.setApplicationDeadline(deadline);

        String result = jobposting.postJob(jobApplication);
        assertNotNull(result);
        assertTrue(result.contains("Job posted successfully"));

        System.out.println(result);

    }

}