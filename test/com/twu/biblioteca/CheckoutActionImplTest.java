package com.twu.biblioteca;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu.CheckoutBookImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu.CheckoutMovieImpl;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.BibliotecaApp;
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
        Customer customer = new Customer("111-1111","aaaa",bookLibrary,null);
        CheckoutBookImpl checkoutBook = new CheckoutBookImpl(bookLibrary);
        LoginImpl.setUser((Customer)customer);
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
        CheckoutBookImpl checkoutBook = new CheckoutBookImpl(bookLibrary);
        LoginImpl.setUser((Customer)customer);

        String expected="That Book is not available so select a different book or fix the spelling error.";
        checkoutBook.doAction(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));

    }

    @Test
    public void testCheckOutValidMovie() throws Exception
    {
        List<Movie> movielist = new SeedData().allMovies();
        Library<Movie> movielibrary = new Library<Movie>(movielist);

        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa",null,movielibrary);
        bibliotecaApp.setLoggedInCustomer(customer);
        Movie checkedOutMovie = movielist.get(0);
        CheckoutMovieImpl checkoutMovie = new CheckoutMovieImpl(movielibrary);
        LoginImpl.setUser((Customer)customer);

        checkoutMovie.doAction(bibliotecaApp);


        assertThat(movielist, not(hasItem(checkedOutMovie)));
    }

    @Test
    public void testCheckOutInvalidMovie() throws IOException
    {
        List<Movie> movielist = new SeedData().allMovies();
        Library<Movie> movielibrary = new Library<Movie>(movielist);

        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("100");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("111-1111","aaaa",null,movielibrary);
        bibliotecaApp.setLoggedInCustomer(customer);
        CheckoutMovieImpl checkoutMovie = new CheckoutMovieImpl(movielibrary);
        LoginImpl.setUser((Customer)customer);

        String expected="That movie is not available so select a different movie or fix the spelling error.";
        checkoutMovie.doAction(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}
