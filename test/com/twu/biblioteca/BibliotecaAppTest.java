package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.BibliotecaApp.getBookList;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    ArrayList<Book> booklist;
    BibliotecaApp bibliotecaApp;
    Customer customer;
    Library library;
    @Before
    public void setUp()
    {
        booklist= getBookList();
        bibliotecaApp = new BibliotecaApp();
        customer = new Customer("diana");
        library=new Library(booklist);
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueZero() throws IOException {
        Library library = new Library(booklist);
        int optionChosen=0;
        String expected="You choose option 0";
        String actual =bibliotecaApp.performActions(optionChosen, library,customer);
        assertEquals(expected,actual);
    }

//    @Test
//    public void testValidatePerformActionShowsBookListOnInputValueOne() throws IOException {
//        Library library = new Library(booklist);
//        int optionChosen=1;
//        String expected="You choose option 1";
//        String actual =bibliotecaApp.performActions(optionChosen,library);
//
//        assertEquals(expected,actual);
//    }

    @Test
    public void testValidatePerformActionRerunsOnInvalidInputValue() throws IOException {
        Library library = new Library(booklist);
        int optionChosen=-1;
        String expected="You have entered a wrong input.";
        String actual =bibliotecaApp.performActions(optionChosen, library,customer);
        assertEquals(expected,actual);
    }



}