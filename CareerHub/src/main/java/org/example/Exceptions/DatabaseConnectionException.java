package org.example.Exceptions;

public class DatabaseConnectionException extends Exception{
    public DatabaseConnectionException(String message, Exception e)
    {
        super(message);
    }
}
