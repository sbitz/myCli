package com.stevebitzel;

import java.io.*;
import java.util.Scanner;


public class EchoDriver {

    public static void run(String[] args) {

        InputStream input = System.in;
        PrintStream output = System.out; // NOSONAR

        output.println("Hello. What do you have to say?");
        for (int i = 0; i < args.length - 1; i++) {
            if ("-in".equals(args[i])) {
                String filename = args[++i]; // NOSONAR
                try {
                    input = new FileInputStream(filename); // NOSONAR
                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + filename); // NOSONAR
                    System.exit(1);
                }
            }
            if ("-out".equals(args[i])) {
                String filename = args[++i]; // NOSONAR
                try {
                    output = new PrintStream(filename); // NOSONAR
                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + filename); // NOSONAR
                    System.exit(2);
                }
            }
        }
        run(input, output);
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
        run(args);
    }
}
