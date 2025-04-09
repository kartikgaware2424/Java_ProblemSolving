package org.example.Main;

import org.example.Model.Applicant;
import org.example.Model.JobApplication;
import org.example.Model.JobListing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Applicant applicant;
    private JobApplication jobApplication;
    private JobListing jobListing;




    @BeforeEach
    void setUp() {
        applicant = new Applicant();
        jobApplication = new JobApplication();
        jobListing = new JobListing();

    }

    @Test
    void testApplicantProfileCreation() {
        applicant.setFirstName("John");
        applicant.setLastName("Doe");
        applicant.setEmail("john.doe@example.com");
        applicant.setPhone("1234567890");
        applicant.setResume("path/to/resume.pdf");

        assertEquals("John", applicant.getFirstName());
        assertEquals("Doe", applicant.getLastName());
        assertEquals("john.doe@example.com", applicant.getEmail());
        assertEquals("1234567890", applicant.getPhone());
        assertEquals("path/to/resume.pdf", applicant.getResume());
    }

    @Test
    void testJobApplicationSubmission() {
        jobApplication.setJobID(1);
        jobApplication.setApplicantID(2);
        jobApplication.setCoverLetter("This is a cover letter.");
        Date applicationDate = new Date();
        jobApplication.setApplicationDate(applicationDate);
        assertEquals(1, jobApplication.getJobID());
        assertEquals(2, jobApplication.getApplicantID());
        assertEquals("This is a cover letter.", jobApplication.getCoverLetter());
        assertEquals(applicationDate, jobApplication.getApplicationDate());
    }

    @Test
    void testJobListingCreation() {
        jobListing.setCompanyID(1);
        jobListing.setJobTitle("Java Developer");
        jobListing.setJobDescription("Responsible for backend APIs.");
        jobListing.setJobLocation("Pune");
        jobListing.setSalary(90000.0);
        jobListing.setJobType("Full-time");
        Date postedDate = new Date();

        Date deadline = new Date(System.currentTimeMillis() + 86400000);
        jobListing.setPostedDate(postedDate);
        jobListing.setApplicationDeadline(deadline);
        assertEquals(1, jobListing.getCompanyID());
        assertEquals("Java Developer", jobListing.getJobTitle());
        assertEquals("Responsible for backend APIs.", jobListing.getJobDescription());
        assertEquals("Pune", jobListing.getJobLocation());
        assertEquals(90000.0, jobListing.getSalary());
        assertEquals("Full-time", jobListing.getJobType());
        assertEquals(postedDate, jobListing.getPostedDate());
        assertEquals(deadline, jobListing.getApplicationDeadline());
    }


}