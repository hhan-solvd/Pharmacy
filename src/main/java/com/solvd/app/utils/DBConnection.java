package com.solvd.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return DriverManager.getConnection(props.getProperty("db.URL"),
                    props.getProperty("db.USER"), props.getProperty("db.PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
