package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class LogoutImpl implements MenuAction {

    @Override
    public void doAction(BibliotecaApp bibliotecaApp) {
        InputOutputDevice ioDevice=bibliotecaApp.getIoDevice();
        if(bibliotecaApp.getLoggedInCustomer() == null)
            ioDevice.writeOutput("You are not logged in!");

        if(bibliotecaApp.getLoggedInCustomer()!=null)
        {
            bibliotecaApp.setLoggedInCustomer(null);
            ioDevice.writeOutput("You are now logged out!");
        }
    }

    @Override
    public String printMenu() {
        return "Logout.";
    }
}
