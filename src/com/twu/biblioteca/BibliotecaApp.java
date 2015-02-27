package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;


interface InputOutputDevice{
    String readInput() throws IOException;
    void writeOutput(String s, Object... args);
}

public class BibliotecaApp {

    private InputOutputDevice ioDevice;

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
                ioDevice.writeOutput("Exiting!!! ");
                break;
            case 1:
                printBookDetails(library);
                break;
            case 2:
                checkoutBook(library,customer);
                break;
            case 3:
                returnBook(library,customer);
                break;

            default:
                ioDevice.writeOutput("You have entered a wrong input.\nSelect a valid option from the menu list to go forward!");
                break;
        }
    }


    public void printBookDetails (Library library)throws IOException
    {
        if(library.getBookList().size()!=0) {
            ioDevice.writeOutput("The list of books You can choose from are:\n");

            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-10s%-30s%-22s%s\n", "Book Id", "Book Name", "Book Author", "Publishing Year");
            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            for (int i = 0; i < library.getBookList().size(); i++) {
                ioDevice.writeOutput("|%-10d|%-30s|%-30s|%-11s|\n", i + 1, library.getBookList().get(i).getBookName(), library.getBookList().get(i).getAuthorName(), library.getBookList().get(i).getYearOfPublishing());
            }
            ioDevice.writeOutput("");

        }
        else
        {
            ioDevice.writeOutput("Sorry.. No books left to checkout.");
        }
    }

    public void returnBook(Library library,Customer customer) throws IOException
    {
        ioDevice.writeOutput("Enter the book name You want to return ");
        String bookToReturn = ioDevice.readInput();
        try
        {
            Book returnedBook = customer.returnBook(bookToReturn);
            library.addBookToRepository(returnedBook);
            ioDevice.writeOutput("Thank you for returning the book.");
        }
        catch(BookNotValidException e)
        {
            ioDevice.writeOutput("That is not a valid book to return.");
        }
    }

    public void checkoutBook(Library library, Customer customer) throws IOException
    {
        String bookId;
        ioDevice.writeOutput("Enter the book id you want to checkout from the following list of books.");
        boolean isNotValidBookId;
        do {
            printBookDetails(library);
            bookId = ioDevice.readInput();
            isNotValidBookId = !bookId.matches("[0-9]+");
            if(isNotValidBookId)
                ioDevice.writeOutput("Please enter a numeric book id value");
        }while(isNotValidBookId);

        int optionChosen = Integer.parseInt(bookId);
        try
        {
            Book removedBook = library.removeBookFromList(optionChosen);
            customer.checkOutBook(removedBook);
            ioDevice.writeOutput("Thank you! Enjoy the book.");
        }
        catch (BookNotValidException e)
        {
            String line = "That book is not available so select a different book or fix the spelling error.";
            ioDevice.writeOutput(line);
        }
    }

    public void getInputFromUserAndWriteSomething() throws IOException {
        String input = ioDevice.readInput();
        ioDevice.writeOutput(input + " Appended");
    }

}
