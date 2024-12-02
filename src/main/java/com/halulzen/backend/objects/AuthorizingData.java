package com.halulzen.backend.objects;

public class AuthorizingData {
    private String username;
    private String password;
    private String website;

    public AuthorizingData() {}

    public AuthorizingData(String username, String password, String website) {
        this.username = username;
        this.password = password;
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "AuthorizingData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
