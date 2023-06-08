package com.solvd.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertiesReader {
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/db.properties";

    public static Properties getProperties() {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream(PROPERTIES_FILE_PATH)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }
}