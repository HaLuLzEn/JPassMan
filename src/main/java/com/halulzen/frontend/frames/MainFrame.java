package com.halulzen.frontend.frames;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;

    public static MainFrame getInstance() {
        if (instance == null) {
            new MainFrame();
        }
        return instance;
    }


    public MainFrame() {
        instance = this;
    }
}
