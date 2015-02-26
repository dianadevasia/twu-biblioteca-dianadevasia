package com.twu.biblioteca;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.BibliotecaApp.getBookList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class BibliotecaAppTest {

    @Test
    public void testShowMenu(){
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        String expected="1. List Books\n2. Checkout Books\n3. Return Books\n0. Quit";
        bibliotecaApp.showMenu();

        assertThat(ioDevice.getActualWrittenOutput(),is(expected));
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueZero() throws IOException
    {
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        int optionChosen=0;
        String expected = "Exiting!!! ";
        bibliotecaApp.performActions(optionChosen, library, customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueTwo() throws IOException
    {
        MockInputOutputDevice ioDevice = new MockInputOutputDevice("2");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        int optionChosen=2;
        String expected = "Thank you! Enjoy the book.";
        bibliotecaApp.performActions(optionChosen, library, customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueThree() throws IOException
    {
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        Customer customer = new Customer("diana");
        Book removedBook = booklist.remove(0);
        customer.checkOutBook(removedBook);

        MockInputOutputDevice ioDeviceForReturn = new MockInputOutputDevice("Learning Python");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDeviceForReturn);

        int optionChosen=3;

        String expected = "Thank you for returning the book.";
        bibliotecaApp.performActions(optionChosen, library, customer);

        assertThat(ioDeviceForReturn.getActualWrittenOutput(), is(expected));
    }

//    @Test
//    public void testValidatePerformActionShowsBookListOnInputValueOne() throws IOException {
//
//        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
//        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
//        Customer customer = new Customer("diana");
//        ArrayList<Book> booklist = getBookList();
//        Library library = new Library(booklist);
//        int optionChosen=1;
//
//        String expected="You choose option 1";
//        bibliotecaApp.performActions(optionChosen,library,customer);
//
//        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
//    }

    @Test
    public void testValidatePerformActionRerunsOnInvalidInputValue() throws IOException
    {

        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        int optionChosen=-1;
        String expected="You have entered a wrong input.\nSelect a valid option from the menu list to go forward!";
        bibliotecaApp.performActions(optionChosen, library,customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testCheckOutValidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDevice = new MockInputOutputDevice("1");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        Book checkedOutBook = booklist.get(0);

        bibliotecaApp.checkoutBook(library,customer);
        assertThat(booklist, not(hasItem(checkedOutBook)));
    }


    @Test
    public void testCheckOutInvalidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDevice = new MockInputOutputDevice("100");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        String expected="That book is not available so select a different book or fix the spelling error.";
        bibliotecaApp.checkoutBook(library, customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testReturnValidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDeviceForCheckout = new MockInputOutputDevice("1");
        MockInputOutputDevice ioDeviceForReturn = new MockInputOutputDevice("Learning Python");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDeviceForCheckout);

        bibliotecaApp.checkoutBook(library, customer);
        bibliotecaApp.setIoDevice(ioDeviceForReturn);
        bibliotecaApp.returnBook(library,customer);

        String expected="Thank you for returning the book.";
        assertThat(ioDeviceForReturn.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testReturnInValidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDeviceForCheckout = new MockInputOutputDevice("1");
        MockInputOutputDevice ioDeviceForReturn = new MockInputOutputDevice("Learning Python1");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDeviceForCheckout);

        bibliotecaApp.checkoutBook(library, customer);
        bibliotecaApp.setIoDevice(ioDeviceForReturn);
        bibliotecaApp.returnBook(library,customer);

        String expected="That is not a valid book to return.";
        assertThat(ioDeviceForReturn.getActualWrittenOutput(), is(expected));
    }
}