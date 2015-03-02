package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.view.CheckoutMenuActionImpl;
import com.twu.biblioteca.view.MenuAction;
import com.twu.biblioteca.view.ReturnBookMenuActionImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.view.BibliotecaApp.getBookList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReturnBookMenuActionImplTest {

    @Test
    public void testReturnValidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDeviceForCheckout = new MockInputOutputDevice("1");
        MockInputOutputDevice ioDeviceForReturn = new MockInputOutputDevice("Learning Python");
        MenuAction checkOutBookMenuAction = new CheckoutMenuActionImpl();
        MenuAction returnBookmenuAction = new ReturnBookMenuActionImpl();

        checkOutBookMenuAction.doAction(library, customer, ioDeviceForCheckout);

        returnBookmenuAction.doAction(library, customer, ioDeviceForReturn);

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

        MenuAction checkOutBookMenuAction = new ReturnBookMenuActionImpl();
        MenuAction returnBookmenuAction = new ReturnBookMenuActionImpl();

        checkOutBookMenuAction.doAction(library, customer, ioDeviceForCheckout);
        returnBookmenuAction.doAction(library, customer, ioDeviceForReturn);

        String expected="That is not a valid book to return.";
        assertThat(ioDeviceForReturn.getActualWrittenOutput(), is(expected));
    }


}