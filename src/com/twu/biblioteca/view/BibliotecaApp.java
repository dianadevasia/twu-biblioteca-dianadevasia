package com.twu.biblioteca.view;

import com.twu.biblioteca.Main;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;

import java.io.IOException;
import java.util.List;


public class BibliotecaApp
{
    private InputOutputDevice ioDevice;
    public Menu menu;
    private Customer loggedInCustomer=null;
    private boolean loggedInLibrarian=false;

    public BibliotecaApp(InputOutputDevice ioDevice)
    {
        this.ioDevice = ioDevice;
    }

    public void setIoDevice(InputOutputDevice ioDevice) {
        this.ioDevice = ioDevice;
    }

    public Menu getMenu() {
        return menu;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public InputOutputDevice getIoDevice() {
        return ioDevice;
    }

    public boolean getLoggedInLibrarian() {
        return loggedInLibrarian;
    }

    public void setLoggedInLibrarian(boolean loggedInLibrarian) {
        this.loggedInLibrarian = loggedInLibrarian;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
    }

    public void setMenu(List<MenuAction> menu) {
        this.menu = new Menu(menu);
    }

    private int readInteger() throws IOException
    {
        return ioDevice.readInt("Please enter a numeric value for menu id.");
    }

    public void welcomeMessage() {
        ioDevice.writeOutput("Welcome to The Bangalore Public Library");
    }

    public void menuProcessing() throws IOException
    {
        int outcomeOfMenuActionPerformed = 0;
        int optionChosenForMenuSelection;
        do {
            menu.showMenu(this);

            optionChosenForMenuSelection = readInteger();
            optionChosenForMenuSelection--;

            try {
                outcomeOfMenuActionPerformed=menu.performActions(optionChosenForMenuSelection,this);
            }
            catch (InvalidMenuOptionChoosen e){
                ioDevice.writeOutput("You have entered a wrong input.");
                ioDevice.writeOutput("Select a valid option from the menu list to go forward!");
            }

        } while (outcomeOfMenuActionPerformed != Main.QUITCODE);

    }

    // use for mock. //
    public void getInputFromUserAndWriteSomething() throws IOException {
        String input = ioDevice.readInput();
        ioDevice.writeOutput(input + " Appended");
    }
}
