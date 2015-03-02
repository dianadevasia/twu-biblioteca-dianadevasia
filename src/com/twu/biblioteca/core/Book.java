package com.twu.biblioteca.core;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Book
{
    private String bookName;
    private String authorName;
    private int yearOfPublishing;

    public Book(String bookName, String authorName, int yearOfPublishing)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }
}