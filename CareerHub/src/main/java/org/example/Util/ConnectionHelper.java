package org.example.Util;

import org.example.Exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionHelper {
    public static Connection  getConnection() throws DatabaseConnectionException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("db");
            String Driver = rb.getString("driver");
            String url = rb.getString("url");
            String user = rb.getString("user");
            String password = rb.getString("password");

            Class.forName(Driver);

            return DriverManager.getConnection(url, user, password);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            throw new DatabaseConnectionException("Failed to establish database connection.", e);
        }
    }


}
