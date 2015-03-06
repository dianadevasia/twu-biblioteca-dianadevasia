package com.twu.biblioteca.core;

import com.twu.biblioteca.ActionImplementations.*;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class MenuItemGenerator
{
    public static List<MenuAction> createMenu(Library<Book> bookLibrary,Library<Movie> movieLibrary)
    {
        List<MenuAction>customerMenus = new ArrayList<MenuAction>();
        customerMenus.add(new CheckoutBookImpl(bookLibrary));
        customerMenus.add(new ReturnBookImpl(bookLibrary));
        customerMenus.add(new CheckoutMovieImpl(movieLibrary));
        customerMenus.add(new PrintCustomerDetailsImpl());


        List<MenuAction>adminitems = new ArrayList<MenuAction>();
        adminitems.add(new LibrarianViewImplementationForBooks(bookLibrary));
        adminitems.add(new LibrarianViewImplementationForMovies(movieLibrary));
        adminitems.add(new LeaveMenuAndGoBackImpl());


        List<MenuAction>loginItems = new ArrayList<MenuAction>();
        loginItems.add(new LibrarianLoginImpl(new AdminOptionsImpl(new Menu(adminitems))));
        loginItems.add(new CustomerLoginImpl(new Menu(customerMenus)));
        loginItems.add(new LeaveMenuAndGoBackImpl());

        List<MenuAction> mainMenuItems=new ArrayList<MenuAction>();
        mainMenuItems.add(new QuitMenuActionImpl());
        mainMenuItems.add(new PrintBookNamesMenuActionImpl(bookLibrary));
        mainMenuItems.add(new PrintMoviesAvailableImpl(movieLibrary));
        mainMenuItems.add(new LoginImpl(new Menu(loginItems)));


//        menuitems.add(new QuitMenuActionImpl());
//        menuitems.add(new PrintBookNamesMenuActionImpl(bookLibrary));
//        menuitems.add(new PrintMoviesAvailableImpl(movieLibrary));
//        menuitems.add(new CustomerLoginImpl(new CheckoutBookImpl(bookLibrary)));
//        menuitems.add(new CustomerLoginImpl(new ReturnBookImpl(bookLibrary)));
//        menuitems.add(new CustomerLoginImpl(new CheckoutMovieImpl(movieLibrary)));
//        menuitems.add(new CustomerLoginImpl(new PrintCustomerDetailsImpl()));
//        menuitems.add(new LibrarianLoginImpl());
//        menuitems.add(new LogoutImpl());

        return mainMenuItems;
    }
}
