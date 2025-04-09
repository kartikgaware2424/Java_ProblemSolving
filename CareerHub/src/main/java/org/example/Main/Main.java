package org.example.Main;

import org.example.Dao.*;
import org.example.Exceptions.ApplicationDeadlineHandling;
import org.example.Exceptions.DatabaseConnectionException;
import org.example.Exceptions.InvalidEmailException;
import org.example.Model.Applicant;
import org.example.Model.JobApplication;
import org.example.Model.JobListing;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void JobListingRetrieval() {
        JobListingRetrievalDao dao = new JobListingRetrivelDaoImpl();
        try {
            List<JobListing> joblist = dao.getJobListings();
            for (JobListing jobs : joblist) {
                System.out.println(jobs);
            }
        } catch (DatabaseConnectionException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ApplicantProfileCreation() {
        try {

            Applicant applicant = new Applicant();

            System.out.print("Enter First Name: ");
            applicant.setFirstName(sc.nextLine());

            System.out.print("Enter Last Name: ");
            applicant.setLastName(sc.nextLine());

            System.out.print("Enter Email: ");
            applicant.setEmail(sc.nextLine());

            System.out.print("Enter Phone: ");
            applicant.setPhone(sc.nextLine());

            System.out.print("Enter Resume File Path: ");
            applicant.setResume(sc.nextLine());

            ApplicantDao applicantDao = new ApplicantDaoImpl();
            applicantDao.createApplicantProfile(applicant);
            System.out.println("Applicant profile created successfully!");

        } catch (SQLException | InvalidEmailException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void JobApplicationSubmission() {
        try {

            System.out.println(" Submit Job Application ");
            System.out.print("Enter Job ID: ");
            int jobID = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Applicant ID: ");
            int applicantID = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Cover Letter: ");
            String coverLetter = sc.nextLine();

            JobApplication application = new JobApplication(0, jobID, applicantID, new Date(), coverLetter);

            JobApplicationDao jobApplicationDao = new JobApplicationDaoImpl();
            String result = jobApplicationDao.submitJobApplication(application);

            System.out.println(result);

        } catch (DatabaseConnectionException | SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (ApplicationDeadlineHandling e) {
            throw new RuntimeException(e);
        }
    }


    public static void CompanyJobPosting() {
        try {
            System.out.println(" Post New Job Listing ");
            JobListing jobListing = new JobListing();
            System.out.print("Enter Company ID: ");
            jobListing.setCompanyID(sc.nextInt());
            sc.nextLine();
            System.out.print("Enter Job Title: ");
            jobListing.setJobTitle(sc.nextLine());

            System.out.print("Enter Job Description: ");
            jobListing.setJobDescription(sc.nextLine());

            System.out.print("Enter Job Location: ");
            jobListing.setJobLocation(sc.nextLine());

            System.out.print("Enter Salary: ");
            jobListing.setSalary(sc.nextDouble());
            sc.nextLine();
            System.out.print("Enter Job Type (Full-time/Part-time/Contract): ");
            jobListing.setJobType(sc.nextLine());

            jobListing.setPostedDate(new java.util.Date());

            System.out.print("Enter Application Deadline (YYYY-MM-DD): ");
            String deadlineStr = sc.nextLine();
            java.util.Date deadline = java.sql.Date.valueOf(deadlineStr);
            jobListing.setApplicationDeadline(deadline);

            // Post the job
            JobPostingDao jobPostingDao = new JobPostingDaoImpl();
            String result = jobPostingDao.postJob(jobListing);

            System.out.println(result);

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void SalaryRangeSearch() {
        try {
            System.out.println("Search Jobs by Salary Range");

            System.out.print("Enter Minimum Salary: ");
            double minSalary = sc.nextDouble();

            System.out.print("Enter Maximum Salary: ");
            double maxSalary = sc.nextDouble();

            if (minSalary > maxSalary) {
                System.out.println("Error: Minimum salary cannot be greater than maximum salary.");
                return;
            }

            SalaryRangeQueryDao salaryRangeQueryDao = new SalaryRangeQueryDaoImpl();
            List<JobListing> jobs = salaryRangeQueryDao.searchJobsBySalaryRange(minSalary, maxSalary);

            if (jobs.isEmpty()) {
                System.out.println("No jobs found within the specified salary range.");
            } else {
                System.out.println("-------------------------------------------------------------------");
                for (JobListing job : jobs) {
                    System.out.println(job);
                    System.out.println("-------------------------------------------------------------------");
                }
            }

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println(" CareerHub Job Board ");
            System.out.println("1. View All Job Listings");
            System.out.println("2. Create Applicant Profile");
            System.out.println("3. Submit Job Application");
            System.out.println("4. Post New Job Listing");
            System.out.println("5. Search Jobs by Salary Range");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    JobListingRetrieval();
                    break;
                case 2:
                    ApplicantProfileCreation();
                    break;
                case 3:
                    JobApplicationSubmission();
                    break;
                case 4:
                    CompanyJobPosting();
                    break;
                case 5:
                    SalaryRangeSearch();
                    break;
                case 6:
                    System.out.println("Exiting*** Thank you!");
                    return;
                default:
                    System.out.println("invalid choice.please try again.");
            }
        }
    }

}