package com.twu.biblioteca.view;

import com.twu.biblioteca.core.*;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BibliotecaApp
{
    public static List<Customer> customerList = new ArrayList<Customer>();
    private InputOutputDevice ioDevice;
    public Menu menu;
    private Customer loggedInCustomer=null;

    static{
    customerList.add(new Customer("111-1111", "aaaa","roo","roo@gmail.com",1111111111));
    customerList.add(new Customer("222-2222", "bbbb","koo","koo@gmail.com",1111111112));
    customerList.add(new Customer("333-3333", "cccc","foo","foo@gmail.com",1111111113));
    customerList.add(new Customer("444-4444", "dddd","poo","poo@gmail.com",1111111114));
    }

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

    public void setMenu(List<MenuAction> menu) {
        this.menu = new Menu(menu);
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
    }

    public InputOutputDevice getIoDevice() {
        return ioDevice;
    }

    public static void main(String[] args) throws IOException
    {

        SeedData seedDataInstance = new SeedData();
        Library<Book> bookLibrary = new Library<Book>(seedDataInstance.allBooks());
        Library<Movie> movieLibrary = new Library<Movie>(seedDataInstance.allMovies());

        InputOutputDevice consoleIODevice = new ConsoleInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleIODevice);

        bibliotecaApp.menu=new Menu(MenuItemGenerator.createMenu(bookLibrary, movieLibrary));

        int optionChosen;
        bibliotecaApp.ioDevice.writeOutput("Welcome to The Bangalore Public Library");

        do {
            bibliotecaApp.getMenu().showMenu(bibliotecaApp.ioDevice);

            optionChosen = bibliotecaApp.ioDevice.readInt("Please enter a numeric value for menu id.");

            try {
                bibliotecaApp.menu.performActions(optionChosen,bibliotecaApp);
                System.out.println(bibliotecaApp.loggedInCustomer);
            }
            catch (InvalidMenuOptionChoosen e){
                bibliotecaApp.ioDevice.writeOutput("You have entered a wrong input.\nSelect a valid option from the menu list to go forward!");
            }

        } while (optionChosen != 0);
    }

    public void getInputFromUserAndWriteSomething() throws IOException {
        String input = ioDevice.readInput();
        ioDevice.writeOutput(input + " Appended");
    }
}
