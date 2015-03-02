package com.twu.biblioteca.view;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;

import java.io.*;
import java.util.ArrayList;


public class BibliotecaApp {

    private InputOutputDevice ioDevice;
    private MenuAction menuSelected;


    private void setMenuSelected(MenuAction menuSelected) {
        this.menuSelected = menuSelected;
    }

    public BibliotecaApp(InputOutputDevice ioDevice)
    {
        this.ioDevice = ioDevice;
    }

    public void setIoDevice(InputOutputDevice ioDevice) {
        this.ioDevice = ioDevice;
    }

    public static ArrayList<Book> getBookList() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Learning Python", "Mark Lutz", 2009));
        bookList.add(new Book("HeadFirst Java", "Kathy Sierra", 2005));
        bookList.add(new Book("Test Driven Development", "Kent Beck", 2003));
        bookList.add(new Book("Java (TM) Design Patterns", "James W Cooper", 2000));
        return bookList;
    }


    public static void main(String[] args) throws IOException
    {
        ArrayList<Book> bookList = getBookList();
        Customer customer = new Customer("diana");
        Library library = new Library(bookList);

        InputOutputDevice consoleIODevice = new ConsoleInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleIODevice);

        int optionChosen = 0;
        bibliotecaApp.ioDevice.writeOutput("Welcome to The Bangalore Public Library");

        do {
            bibliotecaApp.showMenu();
            optionChosen=Integer.parseInt(bibliotecaApp.ioDevice.readInput());

            bibliotecaApp.performActions(optionChosen, library, customer);

        } while (optionChosen != 0);


    }

    public void showMenu()
    {
        ioDevice.writeOutput("1. List Books\n2. Checkout Books\n3. Return Books\n0. Quit");
    }

    public void performActions(int optionChosen,Library library, Customer customer)throws IOException
    {
        switch (optionChosen) {

            case 0:
                setMenuSelected(new QuitMenuActionImpl());
                menuSelected.doAction(library,customer,ioDevice);
                break;
            case 1:
                setMenuSelected(new PrintBookMenuActionImpl());
                menuSelected.doAction(library, customer, ioDevice);
                break;
            case 2:
                setMenuSelected(new PrintBookMenuActionImpl());
                setMenuSelected(new CheckoutMenuActionImpl());
                menuSelected.doAction(library, customer, ioDevice);
                break;
            case 3:
                setMenuSelected(new ReturnBookMenuActionImpl());
                menuSelected.doAction(library, customer, ioDevice);
                break;

            default:
                ioDevice.writeOutput("You have entered a wrong input.\nSelect a valid option from the menu list to go forward!");
                break;
        }
    }

    public void getInputFromUserAndWriteSomething() throws IOException {
        String input = ioDevice.readInput();
        ioDevice.writeOutput(input + " Appended");
    }

}
