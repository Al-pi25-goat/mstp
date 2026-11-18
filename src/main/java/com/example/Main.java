package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Thread {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        while (true) {
            System.out.println("WELCOME");
            Thread t = new mioThread(ss);
            t.start();
        }
    }

}