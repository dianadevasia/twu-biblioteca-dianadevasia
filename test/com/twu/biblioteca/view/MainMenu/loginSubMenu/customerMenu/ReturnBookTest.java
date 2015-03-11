package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MockInputOutputDevice;
import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.data.SeedData;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReturnBookTest {

    @Test
    public void testReturnValidBook() throws IOException
    {

        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);
        List<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("1");
        inputValuesToGiveToTest.add("Learning Python");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa",bookLibrary,null);
        Login.setUser((Customer) customer);
        CheckoutBook checkoutBook = new CheckoutBook(bookLibrary);
        checkoutBook.doAction(bibliotecaApp);
        ReturnBook returnBook = new ReturnBook(bookLibrary);

        returnBook.doAction(bibliotecaApp);
        String expected="Thank you for returning.";

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testReturnInValidBook() throws IOException
    {
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);
        List<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("1");
        inputValuesToGiveToTest.add("Learning Python1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa",bookLibrary,null);
        Login.setUser((Customer) customer);
        CheckoutBook checkoutBook = new CheckoutBook(bookLibrary);
        checkoutBook.doAction(bibliotecaApp);
        ReturnBook returnBook = new ReturnBook(bookLibrary);

        returnBook.doAction(bibliotecaApp);
        String expected="That is not a valid book to return.";

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}