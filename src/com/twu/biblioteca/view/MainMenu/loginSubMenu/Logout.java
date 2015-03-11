package com.twu.biblioteca.view.MainMenu.loginSubMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.view.IMenuAction;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class Logout implements IMenuAction {

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp) {
        Login.setUser(null);
        return BibliotecaApp.OutputStatus.QUIT;
    }


    @Override
    public String getMenuName() {
            return "Logout.";
    }
}
