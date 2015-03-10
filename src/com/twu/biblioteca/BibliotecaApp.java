package com.twu.biblioteca;

import com.twu.biblioteca.core.*;
import com.twu.biblioteca.data.CustomerData;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.ConsoleInputOutputDevice;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BibliotecaApp
{
    SeedData seedDataInstance;
    public Library<Book> bookLibrary;
    public Library<Movie> movieLibrary;
    public List<Customer> customerList;
    public Librarian librarian;
    public final static int QUITCODE=1;



    private InputOutputDevice ioDevice;
    public Menu menu;

    private Customer loggedInCustomer=null;

    public BibliotecaApp(InputOutputDevice ioDevice)
    {
        seedDataInstance = new SeedData();
        bookLibrary = new Library<Book>(seedDataInstance.allBooks());
        movieLibrary = new Library<Movie>(seedDataInstance.allMovies());
        customerList = new ArrayList<Customer>(new CustomerData(bookLibrary,movieLibrary).allCustomers());
        librarian=new Librarian("admin","admin",bookLibrary,movieLibrary);
        menu=new Menu(MenuItemGenerator.createMenu(bookLibrary, movieLibrary));
        this.ioDevice = ioDevice;
    }

    public void setIoDevice(InputOutputDevice ioDevice) {
        this.ioDevice = ioDevice;
    }

    public Menu getMenu() {
        return menu;
    }

    public InputOutputDevice getIoDevice() {
        return ioDevice;
    }

    public static void main(String[] args) throws IOException
    {
        InputOutputDevice consoleIODevice = new ConsoleInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleIODevice);

        bibliotecaApp.welcomeMessage();

        bibliotecaApp.menuProcessing();
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

        } while (outcomeOfMenuActionPerformed != BibliotecaApp.QUITCODE);

    }

    // use for mock. //
    public void getInputFromUserAndWriteSomething() throws IOException {
        String input = ioDevice.readInput();
        ioDevice.writeOutput(input + " Appended");
    }
}
