package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LibraryTest {

    @Test(expected=BookNotValidException.class)
    public void testValidateBookId()
    {
        ArrayList<Book> booklist = new ArrayList<Book>();
        booklist.add(new Book("Learning Python", "Mark Lutz", 2009));
        Library library = new Library(booklist);

        library.validateBookId(-1);

        fail("Did not get exception");
    }


    @Test
    public void testRemoveBookFromListSuccessfully()
    {
        ArrayList<Book> booklist = new ArrayList<Book>();
        booklist.add(new Book("Learning Python", "Mark Lutz", 2009));
        Library library = new Library(booklist);

        Book expected=library.removeBookFromList(1);

        assertEquals("Learning Python", expected.getBookName());
    }

    @Test(expected = BookNotValidException.class)
    public void testRemoveInvalidBookFromList()
    {
        ArrayList<Book> booklist = new ArrayList<Book>();
        booklist.add(new Book("Learning Python", "Mark Lutz", 2009));
        Library library = new Library(booklist);

        library.removeBookFromList(2);
    }
}