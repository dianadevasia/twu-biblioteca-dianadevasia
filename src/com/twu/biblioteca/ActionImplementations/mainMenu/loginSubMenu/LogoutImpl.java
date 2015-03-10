package com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class LogoutImpl implements MenuAction {

    @Override
    public int doAction(BibliotecaApp bibliotecaApp) {
        LoginImpl.setUser(null);
        return 1;
    }


    @Override
    public String printMenu() {
            return "Logout.";
    }
}
