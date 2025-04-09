package org.example.Dao;

import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobListing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalaryRangeQueryDaoTest {
    private SalaryRangeQueryDaoImpl salaryRangeQueryDao;

    @BeforeEach
    void setUp() {
        salaryRangeQueryDao = new SalaryRangeQueryDaoImpl();
    }

    @Test
    void testSearchJobsBySalaryRange_Success() throws DatabaseConnectionException, SQLException {

            double minSalary = 50000.0;
            double maxSalary = 100000.0;

            List<JobListing> jobListings = salaryRangeQueryDao.searchJobsBySalaryRange(minSalary, maxSalary);

            assertNotNull(jobListings);
            assertTrue(true);

            if (!jobListings.isEmpty()) {
                JobListing job = jobListings.get(0);
                System.out.println("found job : " + job.getJobTitle() + " with  salary : " + job.getSalary());
                assertTrue(job.getSalary() >= minSalary && job.getSalary() <= maxSalary);
            }

        }

}