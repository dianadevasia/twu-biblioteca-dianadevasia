package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.MenuItemGenerator;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.ConsoleInputOutputDevice;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;

import java.io.IOException;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class Main {
    public static void main(String[] args) throws IOException
    {

        SeedData seedDataInstance = new SeedData();
        Library<Book> bookLibrary = new Library<Book>(seedDataInstance.allBooks());
        Library<Movie> movieLibrary = new Library<Movie>(seedDataInstance.allMovies());

        InputOutputDevice consoleIODevice = new ConsoleInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleIODevice);

        bibliotecaApp.menu=new Menu(MenuItemGenerator.createMenu(bookLibrary, movieLibrary));

        bibliotecaApp.welcomeMessage();

        bibliotecaApp.menuProcessing();

    }
}
