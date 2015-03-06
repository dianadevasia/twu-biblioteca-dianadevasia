package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Librarian;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class LibrarianLoginImpl implements MenuAction {

//    MenuAction adminOption;
    

    public LibrarianLoginImpl(MenuAction adminOption) {
        this.adminOption = adminOption;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp) throws IOException {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        boolean isLibrarianLoggedIn = bibliotecaApp.getLoggedInLibrarian();
        String reply;

        if(isLibrarianLoggedIn == true)
        {
                adminOption.doAction(bibliotecaApp);
        }
        else
        {
            do {
                ioDevice.writeOutput("Enter the userId");
                String userId = ioDevice.readInput();
                ioDevice.writeOutput("Enter the userId");
                String password = ioDevice.readInput();
                isLibrarianLoggedIn = Librarian.validateLibrarian(userId, password);
                if (isLibrarianLoggedIn)
                {
                    bibliotecaApp.setLoggedInLibrarian(true);
                    adminOption.doAction(bibliotecaApp);
                    reply="n";
                }
                else {
                    ioDevice.writeOutput("The user id and password don't match.Do you want to try again");
                    reply = ioDevice.readInput();
                }
            }while(reply.equalsIgnoreCase("y"));
        }
    }

    @Override
    public String printMenu() {
//        return adminOption.printMenu();
        return "Library Login";

    }
}
