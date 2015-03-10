package com.twu.biblioteca.view;

import com.twu.biblioteca.BibliotecaApp;
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

    public Menu(List<MenuAction> menuItems)
    {
        for(int i=0;i<menuItems.size();i++)
            this.menuItems.put(i, menuItems.get(i));
    }

    public void showMenu(BibliotecaApp bibliotecaApp){
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        for(Map.Entry<Integer,MenuAction> each : menuItems.entrySet())
        {
            ioDevice.writeOutput("%d\t%s",each.getKey()+1,each.getValue().printMenu());

        }
    }

    public int performActions(int optionChosen,BibliotecaApp bibliotecaApp)throws IOException,InvalidMenuOptionChoosen
    {
        if(menuItems.containsKey(optionChosen))
        {
            return menuItems.get(optionChosen).doAction(bibliotecaApp);
        }
        else
            throw new InvalidMenuOptionChoosen();
    }

}
