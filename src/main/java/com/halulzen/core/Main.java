package com.halulzen.core;

import com.halulzen.backend.RWFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the Program...");
        RWFactory.init();
        System.out.println("Started");
    }
}
