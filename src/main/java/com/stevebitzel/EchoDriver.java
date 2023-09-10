package com.stevebitzel;

import java.util.Scanner;


public class EchoDriver {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        while(b) {
            String s = scanner.nextLine();
            if ("hello".equalsIgnoreCase(s.trim())) {
                System.out.println("Hello, World!");
                b = false;
            } else {
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}
