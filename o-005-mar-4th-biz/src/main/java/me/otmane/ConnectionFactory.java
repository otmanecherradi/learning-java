package me.otmane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class ConnectionFactory {
    private static Connection instance = null;

    public static Connection getInstance() throws SQLException {
        if(instance == null) {
            String url = "jdbc:postgresql://localhost:5432/school__java";
            instance = DriverManager.getConnection(url, "docker", "docker");

        }
        return instance;
    }
}
