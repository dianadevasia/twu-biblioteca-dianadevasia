package com.twu.biblioteca.ActionImplementations.deletedFiles;

import com.twu.biblioteca.Main;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class CustomerLoginImpl implements MenuAction
{
//    MenuAction menuAction;
    Menu menu;

    public CustomerLoginImpl(){}

    public CustomerLoginImpl(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    private Customer validateCustomer(String customerId, String customerPassword)
    {
        Customer validCustomer = null;
        for (Customer eachCustomer : Main.customerList) {
            if (eachCustomer.getAuthenticationValues().getUserId().equals(customerId) && eachCustomer.getAuthenticationValues().getPassword().equals(customerPassword)) {
                validCustomer = eachCustomer;
                break;
            }
        }
        return validCustomer;
    }

    public Customer loginCustomer(BibliotecaApp bibliotecaApp) throws IOException
    {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        ioDevice.writeOutput("Enter your customer id");
        String customerId = (ioDevice.readInput());
        ioDevice.writeOutput("Enter your password");
        String customerPassword = ioDevice.readInput();
        Customer customer = validateCustomer(customerId, customerPassword);
        return customer;

    }

    @Override
    public int doAction(BibliotecaApp bibliotecaApp) throws IOException {

        Customer customer =bibliotecaApp.getLoggedInCustomer();
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        if(customer == null)
        {
            String reply = null;
            do {
                try {

                /* Works just for console.

                Console console = System.console();
                String customerId = console.readLine("Username: ");
                Username customerPassword = String.valueOf(console.readPassword("Password: "));

                */
                    customer=loginCustomer(bibliotecaApp);
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
//                        menuAction.doAction(bibliotecaApp);
                          executeMenu(bibliotecaApp);
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
//            menuAction.doAction(bibliotecaApp);
            executeMenu(bibliotecaApp);
        }
        return 0;
    }

    public String printMenu()
    {
//        return menuAction.printMenu();
        return "Customer Login";
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

        } while (optionChosenForMenuSelection != 5);
    }
}
