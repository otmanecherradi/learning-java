package me.otmane.assignment.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class ConnectionFactory {
    private static Connection instance = null;

    public static Connection getInstance() {
        if(instance == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/school__assignment";
                instance = DriverManager.getConnection(url, "docker", "docker");
            } catch (Exception e) {
                System.out.println("Failed to create JDBC db connection " + e.toString() + e.getMessage());
            }

        }

        return instance;
    }
}
