package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.core.Customer;
import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


public class CustomerTest {

    @Test
    public void testCheckOutBook() {
        Customer customer = new Customer("111-1111","aabcd");
        Book book = new Book("Xtreme Programming", "Kent Beck", 2000);

        customer.checkOutItem(book);

        assertThat(customer.getBorrowedList(), hasItem(book));
    }

    @Test
    public void testReturnValidBook() {
        Customer customer = new Customer("111-1111","aabcd");
        Book book = new Book("Xtreme Programming", "Kent Beck", 2000);
        customer.checkOutItem(book);

        Book returnedBook = (Book)customer.returnItem("Xtreme Programming");
        assertEquals(book, returnedBook);
        assertThat(customer.getBorrowedList(), not(hasItem(returnedBook)));
    }

    @Test(expected = BookNotValidException.class)
    public void testReturnInValidBook() {
        Customer customer = new Customer("111-1111","aabcd");
        Book book = new Book("Xtreme Programming", "Kent Beck", 2000);
        customer.checkOutItem(book);

        Book returnedBook = (Book)customer.returnItem("Xtreme Programming2");

        assertEquals(returnedBook, null);
        fail("Book Exception not thrown");
    }
}