package com.twu.biblioteca.core;

import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.LogoutImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.librarianMenu.LibrarianViewImplementationForBooks;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.librarianMenu.LibrarianViewImplementationForMovies;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class Librarian extends User
{
    static String  username;
    static String password;

    public static Menu createLibrarianMenu(Library bookLibrary,Library movieLibrary){
        List<MenuAction> adminitems = new ArrayList<MenuAction>();
        adminitems.add(new LogoutImpl());
        adminitems.add(new LibrarianViewImplementationForBooks(bookLibrary));
        adminitems.add(new LibrarianViewImplementationForMovies(movieLibrary));
        Menu librarianMenu = new Menu(adminitems);
        return librarianMenu;
    }

    public Librarian(String username, String password,Library bookLibrary,Library movieLibrary) {
        super(createLibrarianMenu(bookLibrary, movieLibrary));
        Librarian.username = username;
        Librarian.password = password;
    }

    public static boolean validateLibrarian(String username,String password){
        if(Librarian.username.equals(username) && Librarian.password.equals(password))
            return true;
        return false;
    }


}
