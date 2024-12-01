package com.halulzen.frontend.panels;

public class LoginPanel {
    private static LoginPanel instance;

    public static LoginPanel getInstance() {
        if (instance == null) {
            new LoginPanel();
        }
        return instance;
    }


    public LoginPanel() {
        instance = this;
    }
}
