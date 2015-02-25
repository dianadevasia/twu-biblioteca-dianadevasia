package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BibliotecaApp {

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
        Customer customer = new Customer("customer1");
        Library library = new Library(bookList);
        BibliotecaApp app=new BibliotecaApp();

        int optionChosen = 0;
        System.out.println("Welcome to The Bangalore Public Library");

        do {
            app.showMenu();
            optionChosen=Integer.parseInt(app.getInputFromUser());
            app.performActions(optionChosen, library, customer);

        } while (optionChosen != 0);


    }

    private String getInputFromUser() throws IOException
    {
        InputStream inputStream = System.in;
        return getInputFromUser(inputStream);

    }
    private String getInputFromUser(InputStream inputStream) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            return br.readLine();

    }

    public void showMenu()
    {
        System.out.println("Choose the options to go forward");
        System.out.println("1. List Books");
        System.out.println("2. Checkout Books");
        System.out.println("3. Return Books");
        System.out.println("0. Quit");
    }

    public String performActions(int optionChosen,Library library, Customer customer)throws IOException
    {
        String str;
        switch (optionChosen) {

            case 0:
                str = "You choose option 0";
                System.out.println("Exiting!!! ");
                break;
            case 1:
                str="You choose option 1";
                printBookDetails(library);
                break;
            case 2:
                str="You choose option 2";
                checkoutBook(library,customer);
                break;
            case 3:
                str="You choose option 3";
                returnBook(library,customer);
                break;

            default:
                str = "You have entered a wrong input.";
                System.out.println(str);
                System.out.println("Select a valid option from the menu list to go forward!\n");
                break;
        }
        return str;
    }

    private void returnBook(Library library,Customer customer) throws IOException
    {
        System.out.println("Enter the book name You want to return ");
        String bookToReturn = getInputFromUser();
        try
        {
            Book returnedBook = customer.returnBook(bookToReturn);
            library.addBookToRepository(returnedBook);
            System.out.println("Thank you for returning the book.");
        }
        catch(BookNotValidException e)
        {
            System.out.println("That is not a valid book to return.");
        }
    }

    public void printBookDetails (Library library)throws IOException
    {
        if(library.getBookList().size()!=0) {
            System.out.println("The list of books You can choose from are:");

            System.out.println("|--------------------------------------------------------------------------|");
            System.out.printf("|%-3s%-30s%-22s%s\n","Book Id", "Book Name", "Book Author", "Publishing Year");

            System.out.println("|---------------------------------------------------------------------------");
            for (int i = 0; i < library.getBookList().size(); i++) {
                System.out.printf("|%-3d|%-30s|%-30s|%-8s|\n",i+1, library.getBookList().get(i).getBookName(), library.getBookList().get(i).getAuthorName(), library.getBookList().get(i).getYearOfPublishing());
            }
            System.out.println();
        }
        else
        {
            System.out.println("Sorry.. No books left to checkout.");
        }
    }

    public void checkoutBook(Library library, Customer customer) throws IOException
    {
        System.out.println("Enter the book id you want to checkout from the following list of books.");
        printBookDetails(library);
        Book removedBook=null;
        int optionChosen = Integer.parseInt(getInputFromUser());
        try
        {
            removedBook = library.removeBookFromList(optionChosen);
            customer.checkOutBook(removedBook);
            System.out.println("Thank you! Enjoy the book.");
        }
        catch (BookNotValidException e)
        {
                System.out.println("That book is not available so select a different book or fix the spelling error.");
        }
    }
}
