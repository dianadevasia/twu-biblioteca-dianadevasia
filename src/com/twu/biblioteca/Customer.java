package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Customer {
    private String name;
    private List<Book> borrowedBooksList =new ArrayList<Book>();

    public Iterable<Book> getBorrowedBooksList() {
        return borrowedBooksList;
    }

    public Customer(String name) {
        this.name = name;

    }

    public void checkOutBook(Book removedBook) {
        borrowedBooksList.add(removedBook);
    }

    public Book returnBook(String bookToReturn){
        Book returnedBook = returnValidBook(bookToReturn);
        return returnedBook;
    }

    public Book returnValidBook(String bookToReturn) {
        int i;
        Book returnedBook=null;
        for(i=0;i< borrowedBooksList.size();i++){
            if(borrowedBooksList.get(i).getBookName().equals(bookToReturn))
            {
                returnedBook= borrowedBooksList.remove(i);
                break;
            }
        }
        if(returnedBook==null)
            throw new BookNotValidException();
        return returnedBook;
    }
}
