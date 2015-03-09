package com.twu.biblioteca.ActionImplementations.deletedFiles;

import com.twu.biblioteca.core.Librarian;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class LibrarianLoginImpl implements MenuAction {

//    MenuAction adminOption;
    Menu menu;

    public LibrarianLoginImpl(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int doAction(BibliotecaApp bibliotecaApp) throws IOException {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        boolean isLibrarianLoggedIn = bibliotecaApp.getLoggedInLibrarian();
        String reply;

        if(isLibrarianLoggedIn == true)
        {
//                menu.doAction(bibliotecaApp);
                executeMenu(bibliotecaApp);
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
//                    adminOption.doAction(bibliotecaApp);
                    executeMenu(bibliotecaApp);
                    reply="n";
                }
                else {
                    ioDevice.writeOutput("The user id and password don't match.Do you want to try again");
                    reply = ioDevice.readInput();
                }
            }while(reply.equalsIgnoreCase("y"));
        }
        return 0;
    }

    @Override
    public String printMenu() {
//        return adminOption.printMenu();
        return "Library Login";
    }

    private void executeMenu(BibliotecaApp bibliotecaApp) throws IOException {
        int optionChosenForMenuSelection=0;
        do {
            InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
            menu.showMenu(bibliotecaApp);

            optionChosenForMenuSelection = ioDevice.readInt("Enter valid customer sub-menu option.");
            optionChosenForMenuSelection--;

            try {
                menu.performActions(optionChosenForMenuSelection,bibliotecaApp);
            }
            catch (InvalidMenuOptionChoosen e){
                ioDevice.writeOutput("You have entered a wrong input.");
                ioDevice.writeOutput("Select a valid option from the menu list to go forward!");
            }

        } while (optionChosenForMenuSelection != 3);
    }
}
