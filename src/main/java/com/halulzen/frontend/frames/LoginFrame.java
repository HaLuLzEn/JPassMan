package com.halulzen.frontend.frames;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private static LoginFrame instance;

    public static LoginFrame getInstance() {
        if (instance == null) {
            new LoginFrame();
        }
        return instance;
    }


    public LoginFrame() {
        instance = this;
    }
}
