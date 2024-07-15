package com.sems.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbUtil  {
    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagement";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "RSCOE";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connection established.");
            } catch (SQLException e) {
                System.out.println("Connection not created: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
