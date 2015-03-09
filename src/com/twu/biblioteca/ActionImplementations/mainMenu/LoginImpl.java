package com.twu.biblioteca.ActionImplementations.mainMenu;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Librarian;
import com.twu.biblioteca.core.User;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class LoginImpl implements MenuAction {

    static User user;

    public static void setUser(User user) {
        LoginImpl.user = user;
    }

    public static User getUser() {
        return LoginImpl.user;
    }

    private void executeMenu(BibliotecaApp bibliotecaApp,Menu menu) throws IOException {
        int outcomeOfMenuActionPerformed=0;
        do {
            InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
            menu.showMenu(bibliotecaApp);

            int optionChosenForMenuSelection = ioDevice.readInt("Enter valid customer sub-menu option.");
            optionChosenForMenuSelection--;

            try
            {
                outcomeOfMenuActionPerformed=menu.performActions(optionChosenForMenuSelection, bibliotecaApp);
            }
            catch (InvalidMenuOptionChoosen e)
            {
                ioDevice.writeOutput("You have entered a wrong input.");
                ioDevice.writeOutput("Select a valid option from the menu list to go forward!");
            }

        } while (outcomeOfMenuActionPerformed != BibliotecaApp.QUITCODE);
    }


    private User loginUser(BibliotecaApp bibliotecaApp) throws IOException {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        ioDevice.writeOutput("enter your user id");
        String userId = (ioDevice.readInput());
        ioDevice.writeOutput("Enter your password");
        String userPassword = ioDevice.readInput();
        Boolean isLibrarianLoggedIn = Librarian.validateLibrarian(userId, userPassword);
        if (isLibrarianLoggedIn)
            return bibliotecaApp.librarian;
        else {
            Customer customer = Customer.validateCustomer(userId, userPassword,bibliotecaApp.customerList);
            return customer;
        }
    }

    @Override
    public int doAction(BibliotecaApp bibliotecaApp) throws IOException {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        String reply = null;
        do {
            try {

            /*
             Works just for console.

            Console console = System.console();
            String customerId = console.readLine("Username: ");
            Username customerPassword = String.valueOf(console.readPassword("Password: "));

            */
                user = loginUser(bibliotecaApp);
                if (user == null) {
                    ioDevice.writeOutput("Wrong user name & password.Do you want to try again? (y/Y)");
                    reply = ioDevice.readInput();
                }
                else
                {
                    reply = "n";
                    ioDevice.writeOutput("Logged In successfully!!!");
                    setUser(user);
                    System.out.println(this.user);
                    executeMenu(bibliotecaApp, this.user.roleAssociatedMenuList);
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        } while (reply.equalsIgnoreCase("y"));
        return 0;
    }

    @Override
    public String printMenu() {
        return "Login";
    }
}
