package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class LoginImpl implements MenuAction {

    Menu menu;

    public LoginImpl(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp) throws IOException {
        int optionChosenForMenuSelection;
        do {
            menu.showMenu(bibliotecaApp);
            optionChosenForMenuSelection = bibliotecaApp.getIoDevice().readInt("enter valid input for sub menu");
            optionChosenForMenuSelection--;
            menu.performActions(optionChosenForMenuSelection, bibliotecaApp);
        }while(optionChosenForMenuSelection!=2);

    }

    @Override
    public String printMenu() {
        return "Login";
    }
}
