package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class LogoutImpl implements MenuAction {
    @Override
    public void doAction(BibliotecaApp bibliotecaApp) {
        bibliotecaApp.setLoggedInCustomer(null);
    }

    @Override
    public String printMenu() {
        return "Logout.";
    }
}
