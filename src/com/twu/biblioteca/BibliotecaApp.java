package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    public static void main(String[] args)
    {
        ArrayList<Book>bookList=createBookList();
        Library library = new Library(bookList);

        System.out.println("Welcome to The Bangalore Public Library");
        System.out.println("The list of books You can choose from are:");

        library.printBookDetails();
    }

    public static ArrayList<Book> createBookList()
    {
        ArrayList<Book>bookList = new ArrayList<Book>();
        bookList.add(new Book("Learning Python","Mark Lutz",2009));
        bookList.add(new Book("HeadFirst Java","Kathy Sierra",2005));
        bookList.add(new Book("Test Driven Development","Kent Beck",2003));
        bookList.add(new Book("Java (TM) Design Patterns","James W Cooper",2000));
        return bookList;
    }
}
