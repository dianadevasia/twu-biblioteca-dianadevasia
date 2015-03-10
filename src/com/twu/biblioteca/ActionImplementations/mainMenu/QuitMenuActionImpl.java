package com.twu.biblioteca.ActionImplementations.mainMenu;

import com.twu.biblioteca.core.Item;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class QuitMenuActionImpl implements MenuAction<Item> {

    @Override
    public int doAction(BibliotecaApp bibliotecaApp) {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        ioDevice.writeOutput("Exiting!!! ");
        return 1;
    }

    public String printMenu()
    {
        return "Quit";
    }
}
