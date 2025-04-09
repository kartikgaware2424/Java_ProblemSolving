package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobListing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobListingRetrievalDaoTest {
    private JobListingRetrivelDaoImpl jobListingRetrievalDao;

    @BeforeEach
    void setUp() {
        jobListingRetrievalDao = new JobListingRetrivelDaoImpl();
    }

    @Test
    void testGetJobListings_WithRealDatabase() throws DatabaseConnectionException, SQLException {

            List<JobListing> listings = jobListingRetrievalDao.getJobListings();

            assertNotNull(listings);
            assertTrue(true);

            if (!listings.isEmpty()) {
                JobListing job = listings.get(0);
                System.out.println("Job Title: " + job.getJobTitle());
                assertNotNull(job.getJobTitle());
            }
    }
}