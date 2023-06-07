package com.solvd.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() {
        Properties props = DBPropertiesReader.getProperties();

        try {
            return DriverManager.getConnection(
                    props.getProperty("db.URL"),
                    props.getProperty("db.USER"),
                    props.getProperty("db.PASSWORD")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
