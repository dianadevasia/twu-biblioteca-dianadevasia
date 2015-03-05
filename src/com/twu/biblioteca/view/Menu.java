package com.twu.biblioteca.view;

import com.twu.biblioteca.error.InvalidMenuOptionChoosen;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class Menu {

    Map<Integer,MenuAction> menuItems = new HashMap<Integer,MenuAction>();

    Menu(List<MenuAction> menuItems)
    {
        for(int i=0;i<menuItems.size();i++)
            this.menuItems.put(i, menuItems.get(i));
    }

    public void showMenu(InputOutputDevice ioDevice){
        for(int i=0;i< menuItems.size();i++)
        {
            ioDevice.writeOutput("%d\t%s",i,this.menuItems.get(i).printMenu());
        }
    }

    public void performActions(int optionChosen,BibliotecaApp bibliotecaApp)throws IOException,InvalidMenuOptionChoosen
    {
        if(menuItems.containsKey(optionChosen))
        {
            menuItems.get(optionChosen).doAction(bibliotecaApp);
        }
        else
            throw new InvalidMenuOptionChoosen();
    }

}
