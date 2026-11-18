package com.example;

import java.net.ServerSocket;

public class mioThread extends Thread {

    private ServerSocket s;

    mioThread(ServerSocket s) {
        this.s = s;
    }
}
