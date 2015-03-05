package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Item;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class QuitMenuActionImpl implements MenuAction<Item> {

    @Override
    public void doAction(BibliotecaApp bibliotecaApp ) {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        ioDevice.writeOutput("Exiting!!! ");
    }

    public String printMenu()
    {
        return "Quit.";
    }
}
