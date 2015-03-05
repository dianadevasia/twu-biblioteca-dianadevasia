package com.twu.biblioteca.core;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Book extends Item
{
    private int bookId;
    private String authorName;
    private int yearOfPublishing;

    public Book(int bookId,String bookName, String authorName, int yearOfPublishing)
    {
        this.bookId = bookId;
        this.name = bookName;
        this.authorName = authorName;
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public int getBookId() {
        return bookId;
    }
}