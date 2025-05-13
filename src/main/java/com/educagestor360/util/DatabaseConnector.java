package com.educagestor360.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for managing database connections.
 * Provides methods to get and close JDBC connections.
 */
public class DatabaseConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/educagestor360";
    private static final String DB_USER = "your_db_user"; // Replace with your actual database username
    private static final String DB_PASSWORD = "your_db_password"; // Replace with your actual database password

    /**
     * Establishes and returns a connection to the database.
     *
     * @return A {@link Connection} object to the database.
     * @throws SQLException if a database access error occurs or the url is null.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // This exception should ideally be handled more gracefully,
            // perhaps by logging and re-throwing as a runtime exception
            // or a custom application-specific exception.
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Closes the given {@link Connection}, {@link Statement}, and {@link ResultSet}.
     * This method attempts to close each resource and prints an error message
     * if a {@link SQLException} occurs during closing.
     *
     * @param connection The database connection to close. Can be null.
     * @param statement  The statement to close. Can be null.
     * @param resultSet  The result set to close. Can be null.
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing ResultSet: " + e.getMessage());
        }
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing Statement: " + e.getMessage());
        }
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing Connection: " + e.getMessage());
        }
    }

    /**
     * Overloaded close method to close only Connection and Statement.
     *
     * @param connection The database connection to close. Can be null.
     * @param statement  The statement to close. Can be null.
     */
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }
}