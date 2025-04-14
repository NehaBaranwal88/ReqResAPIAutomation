package com.api.utilities;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataUtils {
    public static String readJsonFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
