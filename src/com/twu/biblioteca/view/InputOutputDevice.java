package com.twu.biblioteca.view;

import java.io.IOException;

public interface InputOutputDevice{
    String readInput() throws IOException;
    void writeOutput(String s, Object... args);
    public int readInt(String errorMessage) throws IOException;
}
