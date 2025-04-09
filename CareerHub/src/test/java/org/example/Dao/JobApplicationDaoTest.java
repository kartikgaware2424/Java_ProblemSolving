package org.example.Dao;

import org.example.Exceptions.ApplicationDeadlineHandling;
import org.example.Exceptions.DatabaseConnectionException;
import org.example.Model.JobApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JobApplicationDaoTest {
    private JobApplicationDaoImpl jobApplicationDao;
    private JobApplication jobApplication;

    @BeforeEach
    void setUp() {
        jobApplicationDao = new JobApplicationDaoImpl();
        jobApplication =new JobApplication();
    }
    @Test void JobapplicationDaoTest() throws ApplicationDeadlineHandling, DatabaseConnectionException, SQLException {
        jobApplication.setJobID(1);
        jobApplication.setApplicantID(11);
        Date postedDate = new Date();
        jobApplication.setApplicationDate(postedDate);
        jobApplication.setCoverLetter("This is the test ");

        String result=jobApplicationDao.submitJobApplication(jobApplication);
        assertNotNull(result);
        assertTrue(result.startsWith("Application submitted successfully! Application ID:"));

        System.out.println(result);

    }

}