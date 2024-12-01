package com.halulzen.backend;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public abstract class RWFactory {
    private static final File READ_FILE = new File("./jpwData.json");
    private static String readPW, readUser;
    private static final Gson gson = new Gson();
    private static JsonArray readJson;

    public static void init() {
        try {
            if (checkFileExistence()) {
                try (InputStream stream = Files.newInputStream(READ_FILE.toPath())) {
                    String content = new String(stream.readAllBytes());
                    readJson = gson.fromJson(content, JsonObject.class).getAsJsonArray("Test");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Reading successful");
            } else {
                System.err.println("Error, while reading passwords");
            }
        } catch (IOException e) {
            System.err.println("Initialization failed due to: " + e.getMessage());
        }
    }

    public static boolean save() {
        return false;
    }

    public static boolean checkFileExistence() throws IOException {
        if (READ_FILE.exists()) {
            return true;
        } else {
            if (READ_FILE.createNewFile()) {
                System.out.println("Save-file not found, created new");
                
            } else {
                System.err.println("Save-file not found, unable to create new file");
            }
            return false;
        }
    }

    public static JsonArray getReadJson() {
        return readJson;
    }

    public static void setReadJson(JsonArray readJson) {
        RWFactory.readJson = readJson;
    }
}
