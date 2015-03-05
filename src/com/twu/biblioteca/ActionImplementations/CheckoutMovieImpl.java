package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.error.MovieNotValidException;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 03/03/15.
 */
public class CheckoutMovieImpl implements MenuAction {

    Library<Movie> movieLibrary;

    public CheckoutMovieImpl(Library<Movie> movieLibrary){
        this.movieLibrary=movieLibrary;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp)
    {
            InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
            Customer customer = bibliotecaApp.getLoggedInCustomer();
            ioDevice.writeOutput("Enter the movie id you want to checkout from the above list of movies.");
            try {
                int movieId = ioDevice.readInt("Please enter a valid numeric movie id.");
                customer.checkOutItem(movieId,movieLibrary);
                ioDevice.writeOutput("Thank you! Enjoy the Movie.");
            } catch (MovieNotValidException e) {
                String line = "That movie is not available so select a different movie or fix the spelling error.";
                ioDevice.writeOutput(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public String printMenu(){
        return "Checkout Movie.";
    }
}
