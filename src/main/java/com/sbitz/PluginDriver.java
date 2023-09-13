package com.sbitz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ServiceLoader;


public class PluginDriver {

    public static void run(String[] args) {
        InputStream input = System.in;
        PrintStream output = System.out; // NOSONAR

        ServiceLoader<InputHandler> loader = ServiceLoader.load(InputHandler.class,
                PluginDriver.class.getClassLoader());


        Iterator<InputHandler> it = loader.iterator();
        if (!it.hasNext()) {
            System.err.println("No Input Handlers found on classpath.");
            System.exit(-1);
        }
        InputHandler handler = it.next();
        if (it.hasNext()) {
            System.out.println("Choose a handler: ");
            String format = "%n: %s";
            System.out.println("1: " + handler.getClass().getName());
            int i = 2;
            while (it.hasNext()) {
                System.out.println( (i++) + ": " + it.next().getClass().getName());
            }
            Scanner s = new Scanner(input);
            int choice = 0;
            try {
                choice = s.nextInt();
            } catch (Exception e) {
                System.err.println("Unexpected input. Exiting.");
                System.exit(-2);
            }
            i= 1;
            for (it = loader.iterator(); i < choice; i++) it.next();
            handler = it.next();
        }


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
        run(input, output, handler);
    }

    public static void run(InputStream in, PrintStream out, InputHandler handler) {
        Scanner scanner = new Scanner(in);
        String s;
        do {
            s = scanner.nextLine();
            handler.process(s);
        } while(!handler.exit(s));
    }

    public static void main(String[] args) {
        run(args);
    }
}
