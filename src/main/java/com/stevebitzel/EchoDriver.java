package com.stevebitzel;

import java.io.*;
import java.util.Scanner;


public class EchoDriver {

    public static void run() {
        run(System.in, System.out); //NOSONAR
    }

    public static void run(InputStream in, PrintStream out) {
        Scanner scanner = new Scanner(in);
        boolean b = true;
        while(b) {
            String s = scanner.nextLine();
            if ("hello".equalsIgnoreCase(s.trim())) {
                out.println("Hello, World!");
                b = false;
            } else {
                out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}
