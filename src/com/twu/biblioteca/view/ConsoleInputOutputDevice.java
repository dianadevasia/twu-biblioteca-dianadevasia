package com.twu.biblioteca.view;

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
    public void writeOutput(String msg, Object... args) {
        System.out.printf(msg, args);
        System.out.println();
    }

    @Override
    public int readInt(String message) throws IOException {
        String selectedId;
        boolean isNotValidMovieId;
        do
        {
            selectedId = readInput();
            isNotValidMovieId = !selectedId.matches("[0-9]+");
            if(isNotValidMovieId)
                writeOutput(message);
        }while(isNotValidMovieId);
        return Integer.parseInt(selectedId);
    }

}
