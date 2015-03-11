package com.twu.biblioteca.view.MainMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MockInputOutputDevice;
import com.twu.biblioteca.view.IMenuAction;
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
public class QuitTest {
    @Test
    public void testValidatePerformActionForQuitApp() throws IOException
    {

        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        int optionChosen=0;

        IMenuAction quitmenu=new Quit();
        List<IMenuAction> IMenuActionList =new ArrayList<IMenuAction>();
        IMenuActionList.add(quitmenu);
        bibliotecaApp.setMenu(IMenuActionList);

        String expected = "Exiting!!! ";
        BibliotecaApp.OutputStatus output = bibliotecaApp.getMenu().performActions(optionChosen, bibliotecaApp);

        BibliotecaApp.OutputStatus expectedOutput = BibliotecaApp.OutputStatus.QUIT;
        assertEquals(output, expectedOutput);
        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }
}
