package com.twu.biblioteca.core;

/**
 * Created by dianadevasia on 02/03/15.
 */
public class Movie extends Item{
    int movieId;
    int movieYear;
    String director;
    int movieRating;

    public Movie(int movieId,String name, String director,int movieYear) {
        this.movieId = movieId;
        this.name = name;
        this.movieYear = movieYear;
        this.director = director;
        this.movieRating = 0;
    }

    public Movie(int movieId,String name, String director,int movieYear, int movieRating) {
        this.movieId = movieId;
        this.name = name;
        this.director = director;
        this.movieYear = movieYear;
        this.movieRating = movieRating;
    }

    public String getName() {
        return name;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public String getDirector() {
        return director;
    }

    public int getMovieRating() {
        return movieRating;
    }
}


