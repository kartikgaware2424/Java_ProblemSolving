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

public class JobListingRetrivelDaoImpl implements JobListingRetrievalDao {
    Connection connection;
    PreparedStatement pst;
    @Override
    public List<JobListing> getJobListings() throws DatabaseConnectionException, SQLException {
        List<JobListing> joblist=new ArrayList<>();
       try
       {
           connection= ConnectionHelper.getConnection();
           String cmd="select j.*, c.CompanyName from Jobs j join Companies c ON j.CompanyID = c.CompanyID";
           pst=connection.prepareStatement(cmd);
           ResultSet resultSet= pst.executeQuery();
           JobListing jobs=null;

           while(resultSet.next())
           {
               jobs=new JobListing(resultSet.getInt("JobID"),
                       resultSet.getInt("CompanyID"),
                       resultSet.getString("JobTitle"),
                       resultSet.getString("JobDescription"),
                       resultSet.getString("JobLocation"),
                       resultSet.getDouble("Salary"),
                       resultSet.getString("JobType"),
                       resultSet.getDate("PostedDate"),
                       resultSet.getDate("ApplicationDeadline"),
                       resultSet.getString("companyname"));
               joblist.add(jobs);
           }
       }catch(SQLException e)
       {
           e.printStackTrace();;
       }
        return joblist;
    }
}
