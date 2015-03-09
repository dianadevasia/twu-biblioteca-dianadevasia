package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.error.BookNotValidException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LibraryTest {

    @Test(expected=BookNotValidException.class)
    public void testValidateBookId()
    {
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> library = new Library<Book>(booklist);

        library.validateId(-1);

        fail("Did not get exception");
    }

    @Test
    public void testRemoveBookFromListSuccessfully()
    {
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> booklibrary = new Library<Book>(booklist);
        Book bookToRemove=booklist.get(0);
        Customer customer = new Customer("111-1111","aabcd",booklibrary,null);

        booklibrary.removeItemFromList(1, customer);

        assertThat(booklibrary.getListOfItemsPresentInLibrary(), not(hasItem(bookToRemove)));
    }

    @Test(expected = BookNotValidException.class)
    public void testRemoveInvalidBookFromList()
    {
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> booklibrary = new Library<Book>(booklist);

        Customer customer = new Customer("111-1111","aabcd",booklibrary,null);

        booklibrary.removeItemFromList(10,customer);

        fail("Did not get exception");
    }
}