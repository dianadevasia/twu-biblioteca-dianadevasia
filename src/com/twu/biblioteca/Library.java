package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by dianadevasia on 25/02/15.
 */
class Library
{
    private ArrayList<Book> bookList;

    public ArrayList<Book> getBookList() {
        return bookList;
    }


    public Library(ArrayList<Book> bookList)
    {
        this.bookList = bookList;
    }

    public void validateBookId(int optionChosen)
    {
        int bookLimitCrossed = bookList.size() + 1;
        if(optionChosen<1 || optionChosen >= bookLimitCrossed)
            throw new BookNotValidException();
    }

    public Book removeBookFromList(int optionChosen)
    {
        validateBookId(optionChosen);
        Book removedBook = bookList.get(optionChosen-1);
        bookList.remove(optionChosen-1);
        return removedBook;

    }

    public void addBookToRepository(Book returnedBook) {
        bookList.add(returnedBook);
    }
}
