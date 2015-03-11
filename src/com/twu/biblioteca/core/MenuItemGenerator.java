package com.twu.biblioteca.core;

import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.view.MainMenu.Quit;
import com.twu.biblioteca.view.IMenuAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class MenuItemGenerator
{
    public static List<IMenuAction> createMenu(Library<Book> bookLibrary,Library<Movie> movieLibrary)
    {
        List<IMenuAction> mainMenuItems=new ArrayList<IMenuAction>();
        mainMenuItems.add(new Login());
        mainMenuItems.add(new Quit());
        return mainMenuItems;
    }
}
