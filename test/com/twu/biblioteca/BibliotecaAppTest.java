package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.twu.biblioteca.BibliotecaApp.createBookList;
import static com.twu.biblioteca.BibliotecaApp.performActions;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    ArrayList<Book> booklist;
    @Before
    public void setUp() throws Exception {
        booklist=createBookList();
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueTwo() throws Exception {
        Library library = new Library(booklist);
        int optionChosen=2;
        String expected="You choose option 2";
        String actual =performActions(optionChosen,library);
        assertEquals(expected,actual);
    }

    @Test
    public void testValidatePerformActionShowsBookListOnInputValueOne() throws Exception {
        Library library = new Library(booklist);
        int optionChosen=1;
        String expected="You choose option 1";
        String actual =performActions(optionChosen,library);
        assertEquals(expected,actual);
    }

    @Test
    public void testValidatePerformActionRerunsOnInvalidInputValue() throws Exception {
        Library library = new Library(booklist);
        int optionChosen=3;
        String expected="You have entered a wrong input.";
        String actual =performActions(optionChosen,library);
        assertEquals(expected,actual);
    }


}