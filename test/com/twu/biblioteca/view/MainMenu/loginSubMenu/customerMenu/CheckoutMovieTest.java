package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MockInputOutputDevice;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.MainMenu.Login;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by dianadevasia on 11/03/15.
 */
public class CheckoutMovieTest {
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
        CheckoutMovie checkoutMovie = new CheckoutMovie(movielibrary);
        Login.setUser((Customer) customer);

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
        CheckoutMovie checkoutMovie = new CheckoutMovie(movielibrary);
        Login.setUser((Customer) customer);

        String expected="That movie is not available so select a different movie or fix the spelling error.";
        checkoutMovie.doAction(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

}
