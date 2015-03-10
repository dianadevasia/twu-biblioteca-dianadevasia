package com.twu.biblioteca;

import com.twu.biblioteca.ActionImplementations.mainMenu.QuitMenuActionImpl;
import com.twu.biblioteca.view.MenuAction;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class QuitMenuImplTest {
    @Test
    public void testValidatePerformActionForQuitApp() throws IOException
    {

        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        int optionChosen=0;

        MenuAction quitmenu=new QuitMenuActionImpl();
        List<MenuAction> menuActionList=new ArrayList<MenuAction>();
        menuActionList.add(quitmenu);
        bibliotecaApp.setMenu(menuActionList);

        String expected = "Exiting!!! ";
        int output = bibliotecaApp.getMenu().performActions(optionChosen, bibliotecaApp);

        int expectedOutput = 1;
        assertEquals(output, expectedOutput);
        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}
