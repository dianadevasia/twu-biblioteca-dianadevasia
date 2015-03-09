package com.twu.biblioteca;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu.CheckoutBookImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu.ReturnBookImpl;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.BibliotecaApp;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReturnBookMenuActionImplTest {

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
        LoginImpl.setUser((Customer) customer);
        CheckoutBookImpl checkoutBook = new CheckoutBookImpl(bookLibrary);
        checkoutBook.doAction(bibliotecaApp);
        ReturnBookImpl returnBook = new ReturnBookImpl(bookLibrary);

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
        LoginImpl.setUser((Customer)customer);
        CheckoutBookImpl checkoutBook = new CheckoutBookImpl(bookLibrary);
        checkoutBook.doAction(bibliotecaApp);
        ReturnBookImpl returnBook = new ReturnBookImpl(bookLibrary);

        returnBook.doAction(bibliotecaApp);
        String expected="That is not a valid book to return.";

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}