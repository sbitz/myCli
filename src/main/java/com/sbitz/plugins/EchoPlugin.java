package com.sbitz.plugins;

import com.sbitz.InputHandler;


public class EchoPlugin implements InputHandler {
    @Override
    public boolean exit(String input) {
        return "hello".equalsIgnoreCase(input.trim());
    }

    @Override
    public void process(String input) {
        System.out.println(input);
        System.out.println(input);
    }
}
