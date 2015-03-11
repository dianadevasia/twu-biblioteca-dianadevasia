package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MockInputOutputDevice;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.MainMenu.Login;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CheckoutBookTest {

    @Test
    public void testCheckOutValidBook() throws IOException
    {
        List<Book> booklist = new SeedData().allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);

        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("1");

        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Book checkedOutBook = booklist.get(0);
        Customer customer = new Customer("111-1111","aaaa",bookLibrary,null);
        CheckoutBook checkoutBook = new CheckoutBook(bookLibrary);
        Login.setUser((Customer) customer);
        checkoutBook.doAction(bibliotecaApp);

        assertThat(booklist, not(hasItem(checkedOutBook)));
    }

    @Test
    public void testCheckOutInvalidBook() throws IOException
    {
        List<Book> booklist = new SeedData().allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);

        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("100");

        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa",bookLibrary,null);
        bibliotecaApp.setLoggedInCustomer(customer);
        CheckoutBook checkoutBook = new CheckoutBook(bookLibrary);
        Login.setUser((Customer) customer);

        String expected="That Book is not available so select a different book or fix the spelling error.";
        checkoutBook.doAction(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));

    }
}
