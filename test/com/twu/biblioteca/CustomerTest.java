package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.error.BookNotValidException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


public class CustomerTest {

    @Test
    public void testCheckOutBook() {
        Customer customer = new Customer("111-1111","aabcd");
        List<Book> bookList = new ArrayList<Book>();
        Book checkedOutBook = new Book(1, "Xtreme Programming", "Kent Beck", 2000);
        bookList.add(checkedOutBook);

        Library<Book> bookLibrary = new Library<Book>(bookList);
        customer.checkOutItem(1,bookLibrary);

        assertThat(customer.getBorrowedList(), hasItem(checkedOutBook));
    }

    @Test
    public void testReturnValidBook() {
        Customer customer = new Customer("111-1111","aabcd");
        List<Book> bookList = new ArrayList<Book>();
        Book returnedBook = new Book(1, "Xtreme Programming", "Kent Beck", 2000);
        bookList.add(returnedBook);

        Library<Book> bookLibrary = new Library<Book>(bookList);
        customer.checkOutItem(1,bookLibrary);

        customer.returnItem("Xtreme Programming", bookLibrary);
        assertThat(customer.getBorrowedList(), not(hasItem(returnedBook)));
    }

    @Test(expected = BookNotValidException.class)
    public void testReturnInValidBook() {
        Customer customer = new Customer("111-1111","aabcd");
        List<Book> bookList = new ArrayList<Book>();
        Book returnedBook = new Book(1, "Xtreme Programming", "Kent Beck", 2000);
        bookList.add(returnedBook);

        Library<Book> bookLibrary = new Library<Book>(bookList);
        customer.checkOutItem(1,bookLibrary);

        customer.returnItem("Xtreme Programming2", bookLibrary);

        fail("Book Exception not thrown");
    }
}