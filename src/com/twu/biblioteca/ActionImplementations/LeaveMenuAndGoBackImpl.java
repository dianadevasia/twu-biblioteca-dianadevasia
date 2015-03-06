package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class LeaveMenuAndGoBackImpl implements MenuAction {
    @Override
    public void doAction(BibliotecaApp bibliotecaApp) throws IOException {

    }

    @Override
    public String printMenu() {
        return "Go Back To Main Menu";
    }
}
