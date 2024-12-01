package com.halulzen.frontend.panels;

import javax.swing.*;

public class MainPanel extends JPanel {
    private static MainPanel instance;

    public static MainPanel getInstance() {
        if (instance == null) {
            new MainPanel();
        }
        return instance;
    }


    public MainPanel() {
        instance = this;
    }
}
