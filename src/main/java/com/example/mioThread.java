package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class mioThread extends Thread {

    private Socket s;

    public mioThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8), true);

            String frase = in.readLine();
            String[] parti = frase.split(" ");
            if (parti.length != 2 && parti.length > 0) {
                out.println("ERR SYNTAX");
                s.close();
            } else if (!parti[0].equals("LEGGI")) {
                out.println("UNKNOWNCMD");
                s.close();
            } else {
                File cartella = new File("documenti");
                File file = new File(cartella, parti[1]);

                System.out.println(file.getAbsolutePath());
                boolean presente = file.isFile();

                if (!presente) {
                    out.println("ERR NOT FOUND");
                    s.close();
                } else {
                    try (BufferedReader lettoreFile = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                        out.println("OK FILE");
                        out.println();

                        String riga;
                        while ((riga = lettoreFile.readLine()) != null) {
                            out.println(riga);
                        }
                        s.close();
                    } catch (IOException e) {
                    }
                }
            }

        } catch (IOException e) {
        }
    }
}
