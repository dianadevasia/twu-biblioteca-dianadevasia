package com.twu.biblioteca.view.MainMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.core.User;
import com.twu.biblioteca.view.IMenuAction;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;

import java.io.IOException;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class Login implements IMenuAction {

    static User user;

    public static void setUser(User user) {
        Login.user = user;
    }

    public static User getUser() {
        return Login.user;
    }

    private void executeMenu(BibliotecaApp bibliotecaApp,Menu menu) throws IOException {
        bibliotecaApp.menuProcessing(menu);
    }


    private User loginUser(BibliotecaApp bibliotecaApp) throws IOException {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        ioDevice.writeOutput("enter your user id");
        String userId = (ioDevice.readInput());
        ioDevice.writeOutput("Enter your password");
        String userPassword = ioDevice.readInput();
        User loggedinUser = User.validateUser(userId, userPassword,bibliotecaApp.librarianList,bibliotecaApp.customerList);
        return loggedinUser;
    }

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp) throws IOException {
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
                    executeMenu(bibliotecaApp,this.user.roleAssociatedMenuList);
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        } while (reply.equalsIgnoreCase("y"));
        return BibliotecaApp.OutputStatus.CONTINUE;
    }

    @Override
    public String getMenuName() {
        return "Login";
    }
}
