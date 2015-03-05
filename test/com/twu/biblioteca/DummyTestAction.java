package com.twu.biblioteca;

import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class DummyTestAction implements MenuAction {
    @Override
    public void doAction(BibliotecaApp bibliotecaApp) {

    }

    @Override
    public String printMenu() {
        return "dummy string";
    }
}
