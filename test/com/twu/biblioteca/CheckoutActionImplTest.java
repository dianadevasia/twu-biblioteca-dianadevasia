package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.ActionImplementations.CheckoutBookImpl;
import com.twu.biblioteca.view.ActionImplementations.CheckoutMovieImpl;
import com.twu.biblioteca.view.ActionImplementations.CustomerLoginImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CheckoutActionImplTest {

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
        Customer customer = new Customer("111-1111","aaaa");
        bibliotecaApp.setLoggedInCustomer(customer);
        CustomerLoginImpl customerImpl = new CustomerLoginImpl(new CheckoutBookImpl(bookLibrary));


        customerImpl.getMenuAction().doAction(bibliotecaApp);

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
        Customer customer = new Customer("111-1111","aaaa");
        bibliotecaApp.setLoggedInCustomer(customer);
        CustomerLoginImpl customerImpl = new CustomerLoginImpl(new CheckoutBookImpl(bookLibrary));


        String expected="That Book is not available so select a different book or fix the spelling error.";
        customerImpl.getMenuAction().doAction(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));

    }

    @Test
    public void testCheckOutValidMovie() throws Exception
    {
        List<Movie> movielist = new SeedData().allMovies();
        Library<Movie> library = new Library<Movie>(movielist);

        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa");
        bibliotecaApp.setLoggedInCustomer(customer);
        Movie checkedOutMovie = movielist.get(0);
        CustomerLoginImpl customerLoginImpl = new CustomerLoginImpl(new CheckoutMovieImpl(library));

        customerLoginImpl.getMenuAction().doAction(bibliotecaApp);

        assertThat(movielist, not(hasItem(checkedOutMovie)));
    }

    @Test
    public void testCheckOutInvalidMovie() throws IOException
    {
        List<Movie> movielist = new SeedData().allMovies();
        Library<Movie> library = new Library<Movie>(movielist);

        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("100");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa");
        bibliotecaApp.setLoggedInCustomer(customer);
        CustomerLoginImpl customerLoginImpl = new CustomerLoginImpl(new CheckoutMovieImpl(library));

        String expected="That movie is not available so select a different movie or fix the spelling error.";
        customerLoginImpl.getMenuAction().doAction(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}
