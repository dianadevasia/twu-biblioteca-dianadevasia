package com.twu.biblioteca.core;

import com.twu.biblioteca.view.MainMenu.loginSubMenu.Logout;
import com.twu.biblioteca.view.MainMenu.loginSubMenu.LibrarianMenu.RenderBooksBorrowed;
import com.twu.biblioteca.view.MainMenu.loginSubMenu.LibrarianMenu.RenderMoviesBorrowed;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.IMenuAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class Librarian extends User
{
    private Authentication authentication;

    public static Menu createLibrarianMenu(Library bookLibrary,Library movieLibrary){
        List<IMenuAction> adminitems = new ArrayList<IMenuAction>();
        adminitems.add(new Logout());
        adminitems.add(new RenderBooksBorrowed(bookLibrary));
        adminitems.add(new RenderMoviesBorrowed(movieLibrary));
        Menu librarianMenu = new Menu(adminitems);
        return librarianMenu;
    }

    public Librarian(String username, String password,Library bookLibrary,Library movieLibrary) {
        super(createLibrarianMenu(bookLibrary, movieLibrary));
        this.authentication=new Authentication(username, password);
    }

    public Authentication getAuthenticationValues() {
        return authentication;
    }
}
