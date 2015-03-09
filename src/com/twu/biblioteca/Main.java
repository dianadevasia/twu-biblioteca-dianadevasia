package com.twu.biblioteca;

import com.twu.biblioteca.core.*;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.ConsoleInputOutputDevice;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class Main {
    public static List<Customer> customerList;
    public static Librarian librarian;
    public final static int QUITCODE=1;

    public static void main(String[] args) throws IOException
    {
        SeedData seedDataInstance = new SeedData();
        Library<Book> bookLibrary = new Library<Book>(seedDataInstance.allBooks());
        Library<Movie> movieLibrary = new Library<Movie>(seedDataInstance.allMovies());

        customerList = new ArrayList<Customer>();
        customerList.add(new Customer("111-1111", "aaaa","roo","roo@gmail.com",1111111111,bookLibrary,movieLibrary));
        customerList.add(new Customer("222-2222", "bbbb","koo","koo@gmail.com",1111111112,bookLibrary,movieLibrary));
        customerList.add(new Customer("333-3333", "cccc","foo","foo@gmail.com",1111111113,bookLibrary,movieLibrary));
        customerList.add(new Customer("444-4444", "dddd","poo","poo@gmail.com",1111111114,bookLibrary,movieLibrary));

        librarian=new Librarian("admin","admin",bookLibrary,movieLibrary);


        InputOutputDevice consoleIODevice = new ConsoleInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(consoleIODevice);

        bibliotecaApp.menu=new Menu(MenuItemGenerator.createMenu(bookLibrary, movieLibrary));

        bibliotecaApp.welcomeMessage();

        bibliotecaApp.menuProcessing();

    }
}
