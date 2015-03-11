package com.twu.biblioteca.data;

import com.twu.biblioteca.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 11/03/15.
 */
public class LibrarianData {

    private ArrayList<Librarian> librarianList = new ArrayList<Librarian>();

    public LibrarianData(Library<Book> bookLibrary,Library<Movie> movieLibrary){
        librarianList.add(new Librarian("admin", "admin",bookLibrary,movieLibrary));
        librarianList.add(new Librarian("admin1", "admin1",bookLibrary,movieLibrary));
        librarianList.add(new Librarian("admin2", "admin2",bookLibrary,movieLibrary));
        librarianList.add(new Librarian("admin3", "admin3",bookLibrary,movieLibrary));
    }

    public List<Librarian> allLibrarians(){
        return librarianList;
    }
}
