package com.twu.biblioteca.core;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Book extends Item
{
    private String authorName;
    private int yearOfPublishing;

    public Book(String bookName, String authorName, int yearOfPublishing)
    {
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
}