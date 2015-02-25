package com.twu.biblioteca;

/**
 * Created by dianadevasia on 25/02/15.
 */
class Book
{
    String bookName;
    String authorName;
    int yearOfPublishing;

    public Book(String bookName, String authorName, int yearOfPublishing)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearOfPublishing = yearOfPublishing;
    }
}