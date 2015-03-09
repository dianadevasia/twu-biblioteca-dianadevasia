package com.twu.biblioteca;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Librarian;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.view.BibliotecaApp;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoginImplTest {


    @Test
    public void testExecutionOfMethodOnSuccessfuLogin() throws IOException {
        List<String> input=new ArrayList<String>();
        input.add("111-1111");
        input.add("aaaa");
        input.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);


        ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
        customerArrayList.add(new Customer("111-1111", "aaaa", bookLibrary, null));
        bibliotecaApp.customerList= customerArrayList;
        Librarian librarian=new Librarian("admin","admin",null,null);

        LoginImpl menuAction = new LoginImpl();
        int actualOutput = menuAction.doAction(bibliotecaApp);
        int expectedOutput = 0;

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void testOnUnSuccessfuLogin() throws IOException {
        List<String> input=new ArrayList<String>();
        input.add("22-222");
        input.add("aaaa");
        input.add("n");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);

        ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
        customerArrayList.add(new Customer("111-1111", "aaaa", bookLibrary, null));
        bibliotecaApp.customerList= customerArrayList;
        Librarian librarian=new Librarian("admin","admin",null,null);



        LoginImpl menuAction = new LoginImpl();
        int actualOutput = menuAction.doAction(bibliotecaApp);
        int expectedOutput = 0;

        assertEquals(actualOutput, expectedOutput);
    }


}