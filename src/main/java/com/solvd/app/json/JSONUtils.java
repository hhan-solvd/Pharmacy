package com.solvd.app.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class JSONUtils {

    public static <T> void writeJSON(T object, String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String className = object.getClass().getSimpleName();
            objectMapper.writeValue(new File(jsonFilePath + className + ".json"), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readJSON(String jsonFilePath, Class<T> targetType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File jsonFile = new File(jsonFilePath);
            return objectMapper.readValue(jsonFile, targetType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

