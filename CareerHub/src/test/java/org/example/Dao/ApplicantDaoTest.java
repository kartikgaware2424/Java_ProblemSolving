package org.example.Dao;

import org.example.Exceptions.InvalidEmailException;
import org.example.Model.Applicant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantDaoTest {

    private ApplicantDaoImpl applicantDao;
    private Applicant applicant;

    @BeforeEach
    void setUp() {
        applicantDao = new ApplicantDaoImpl();
        applicant=new Applicant();
    }
    @Test
    void testApplicantDao() throws SQLException, InvalidEmailException {
        applicant.setFirstName("kartik");
        applicant.setLastName("Gaware");
        applicant.setEmail("gaware" + System.currentTimeMillis() + "@gmail.com");
        applicant.setPhone("987456321");
        applicant.setResume("resume9.pdf");
        String Result=applicantDao.createApplicantProfile(applicant);
        assertNotNull(Result);
        assertEquals("Applicant Added SuccessFully.....", Result);;

        System.out.println(Result);
    }


}