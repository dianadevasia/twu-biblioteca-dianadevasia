package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* Created by dianadevasia on 26/02/15.
*/
public class ConsoleInputOutputDevice implements InputOutputDevice {
    @Override
    public String readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    @Override
    public void writeOutput(String msg) {
        System.out.printf(msg);
        System.out.println();
    }
}