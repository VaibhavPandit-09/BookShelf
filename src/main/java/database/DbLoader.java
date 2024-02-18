package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbLoader {

    // Database credentials
    private static final String USERNAME = "vaibhav";
    private static final String PASSWORD = "vaibhav";
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1434;databaseName=BookLog;";

    static {
        try {
            // Load SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // Try connecting to the database
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }

    

}
