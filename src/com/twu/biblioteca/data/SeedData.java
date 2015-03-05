package com.twu.biblioteca.data;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 03/03/15.
 */
public class SeedData
{

    private ArrayList<Book> bookList = new ArrayList<Book>();
    private ArrayList<Movie> movieList = new ArrayList<Movie>();

    public SeedData()
    {
        bookList.add(new Book("Learning Python", "Mark Lutz", 2009));
        bookList.add(new Book("HeadFirst Java", "Kathy Sierra", 2005));
        bookList.add(new Book("Test Driven Development", "Kent Beck", 2003));
        bookList.add(new Book("Java (TM) Design Patterns", "James W Cooper", 2000));

        movieList.add(new Movie(1, "Life Of Pie", "Yann Martel", 2006, 7));
        movieList.add(new Movie(2, "Spider Man", "Sam Raimi", 2002, 8));
        movieList.add(new Movie(3, "BatMan", "Christopher Nolan", 2005, 8));
        movieList.add(new Movie(4, "Avengers", "Joss Whedon", 2012, 9));
    }

    public List<Movie> allMovies(){
        return (movieList);
//        return Collections.unmodifiableList(movieList);
     }
    public List<Book> allBooks(){
        return (bookList);
     }
}