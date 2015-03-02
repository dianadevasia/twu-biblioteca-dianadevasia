package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.view.CheckoutMenuActionImpl;
import com.twu.biblioteca.view.MenuAction;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.view.BibliotecaApp.getBookList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class CheckoutMenuActionImplTest {

    @Test
    public void testCheckOutValidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDevice = new MockInputOutputDevice("1");
        Book checkedOutBook = booklist.get(0);
        MenuAction menuAction = new CheckoutMenuActionImpl();

        menuAction.doAction(library, customer, ioDevice);

        assertThat(booklist, not(hasItem(checkedOutBook)));
    }

    @Test
    public void testCheckOutInvalidBook() throws IOException
    {
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);
        MockInputOutputDevice ioDevice = new MockInputOutputDevice("100");
        String expected="That book is not available so select a different book or fix the spelling error.";
        MenuAction menuAction = new CheckoutMenuActionImpl();

        menuAction.doAction(library, customer, ioDevice);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}