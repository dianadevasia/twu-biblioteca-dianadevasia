package com.twu.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;



public class CustomerTest {


    @Test
    public void testCheckOutBook() {
        Customer customer = new Customer("diana");
        Book book = new Book("Xtreme Programming", "Kent Beck", 2000);

        customer.checkOutBook(book);

        assertThat(customer.getBorrowedBooksList(), hasItem(book));
    }

    @Test
    public void testReturnValidBook() {
        Customer customer = new Customer("Diana");
        Book book = new Book("Xtreme Programming", "Kent Beck", 2000);
        customer.checkOutBook(book);

        Book returnedBook = customer.returnBook("Xtreme Programming");

        assertEquals(book,returnedBook);
        assertThat(customer.getBorrowedBooksList(), not(hasItem(returnedBook)));
    }

    @Test(expected = BookNotValidException.class)
    public void testReturnInValidBook() {
        Customer customer = new Customer("Diana");
        Book book = new Book("Xtreme Programming", "Kent Beck", 2000);
        customer.checkOutBook(book);

        Book returnedBook = customer.returnBook("Xtreme Programming2");

        assertEquals(returnedBook, null);
        fail("Book Exception not thrown");
    }





}