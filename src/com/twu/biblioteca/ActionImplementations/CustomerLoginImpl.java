package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class CustomerLoginImpl implements MenuAction
{
    MenuAction menuAction;

    public CustomerLoginImpl(){}

    public CustomerLoginImpl(MenuAction menuAction) {
        this.menuAction = menuAction;
    }

    public MenuAction getMenuAction() {
        return menuAction;
    }

    private Customer validateCustomer(String customerId, String customerPassword)
    {
        Customer validCustomer = null;
        for (Customer eachCustomer : BibliotecaApp.customerList) {
            if (eachCustomer.getAuthenticationValues().getUserId().equals(customerId) && eachCustomer.getAuthenticationValues().getPassword().equals(customerPassword)) {
                validCustomer = eachCustomer;
                break;
            }
        }
        return validCustomer;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp){

        Customer customer =bibliotecaApp.getLoggedInCustomer();
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        if(customer == null)
        {
            String reply = null;
            do {
                try {
                    ioDevice.writeOutput("Enter your customer id");
                    String customerId = (ioDevice.readInput());
                    ioDevice.writeOutput("Enter your password");
                    String customerPassword = ioDevice.readInput();

                // Works just for console.

//                Console console = System.console();
//                String customerId = console.readLine("Username: ");
//                String customerPassword = String.valueOf(console.readPassword("Password: "));

                    customer = validateCustomer(customerId, customerPassword);

                    reply = "n";
                    if (customer == null)
                    {
                        ioDevice.writeOutput("Do you want to try again? (y/Y)");
                        reply = ioDevice.readInput();

                    }
                    else
                    {
                        ioDevice.writeOutput("Logged In successfully!!!");
                        bibliotecaApp.setLoggedInCustomer(customer);
                        menuAction.doAction(bibliotecaApp);

                    }
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
            } while (reply.equalsIgnoreCase("y"));
        }
        else
        {
            menuAction.doAction(bibliotecaApp);
        }
    }

    public String printMenu()
    {
        return menuAction.printMenu();
    }
}
