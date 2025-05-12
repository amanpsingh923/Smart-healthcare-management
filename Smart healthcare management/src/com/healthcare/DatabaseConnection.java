package com.healthcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "root";
        String password = "9160";

        return DriverManager.getConnection(url, user, password);
    }
}
