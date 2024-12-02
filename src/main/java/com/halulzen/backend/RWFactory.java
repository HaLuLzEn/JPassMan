package com.halulzen.backend;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.halulzen.backend.objects.AuthorizingData;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public abstract class RWFactory {
    private static final File READ_FILE = new File("./jpwData.json");
    private static final Gson gson = new Gson();
    private static JsonArray readJson;
    private static final List<AuthorizingData> userData = new ArrayList<>();
    private static SecretKey secretKey;

    static {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void init(String uuid) {
        try {
            if (checkFileExistence()) {
                try (InputStream stream = Files.newInputStream(READ_FILE.toPath())) {
                    String content = new String(stream.readAllBytes());
                    readJson = gson.fromJson(content, JsonObject.class).get("Data").getAsJsonObject().getAsJsonArray(uuid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Reading successful");
                convertJsonToList();
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

    public static List<AuthorizingData> getUserData() {
        return userData;
    }

    public static void syncJsonToList() {
        for (int i = 0; i < readJson.asList().size(); i++) {
            //readJson.get(i).getAsJsonObject().get("username") = userData.get(i).getUsername();
        }
    }

    private static void convertJsonToList() {
        for (int i = 0; i < readJson.size(); i++) {
            String username = readJson.get(i).getAsJsonObject().get("username").toString();
            String password = readJson.get(i).getAsJsonObject().get("password").toString();
            String website = readJson.get(i).getAsJsonObject().get("website").toString();

            username = trimString(username);
            password = trimString(password);
            website = trimString(website);

            userData.add(new AuthorizingData(username, password, website));
        }


        System.out.println(crypt(crypt(userData.get(0).getPassword(), true), false));
        System.out.println(crypt(userData.get(0).getPassword(), true));
    }

    private static String trimString(String value) {
        return value.substring(1, value.length() - 1);
    }

    private static String crypt(String value, boolean encrypt) {
        try {


            if (encrypt) {
                return encrypt(value, secretKey);
            } else {
                return decrypt(value, secretKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }

    public static String encrypt(String value, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String value, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
