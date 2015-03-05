package com.twu.biblioteca.view;

import com.twu.biblioteca.core.Item;

/**
 * Created by dianadevasia on 27/02/15.
 */
public interface MenuAction<T extends Item> {

//    public void doAction(Library<T> library, Customer customer, InputOutputDevice ioDevice);
    public void doAction(BibliotecaApp bibliotecaApp);
    public String printMenu();

}
