package com.twu.biblioteca.view.MainMenu;

import com.twu.biblioteca.core.Item;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.IMenuAction;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class Quit implements IMenuAction<Item> {

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp) {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        ioDevice.writeOutput("Exiting!!! ");
        return BibliotecaApp.OutputStatus.QUIT;
    }

    public String getMenuName()
    {
        return "Quit";
    }
}
