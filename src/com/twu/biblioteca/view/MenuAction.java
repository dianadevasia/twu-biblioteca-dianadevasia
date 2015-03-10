package com.twu.biblioteca.view;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.core.Item;

import java.io.IOException;

/**
 * Created by dianadevasia on 27/02/15.
 */
public interface MenuAction<T extends Item> {

//    public void doAction(Library<T> library, Customer customer, InputOutputDevice ioDevice);
    public int doAction(BibliotecaApp bibliotecaApp) throws IOException;
    public String printMenu();

}
