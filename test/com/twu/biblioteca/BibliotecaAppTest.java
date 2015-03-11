package com.twu.biblioteca;

import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.IMenuAction;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MainMenu.Quit;
import com.twu.biblioteca.view.MockInputOutputDevice;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {

    @Test
    public void testShowMenu(){
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        String expected="2\tQuit";
        bibliotecaApp.getMenu().showMenu(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(),is(expected));
    }

    @Test(expected = InvalidMenuOptionChoosen.class)
    public void testValidatePerformActionRerunsOnInvalidInputValue() throws IOException
    {
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        IMenuAction quitmenu=new Quit();
        List<IMenuAction> IMenuActionList =new ArrayList<IMenuAction>();
        IMenuActionList.add(quitmenu);
        bibliotecaApp.setMenu(IMenuActionList);

        int optionChosen=-1;
        String expected="You have entered a wrong input.\nSelect a valid option from the menu list to go forward!";

        bibliotecaApp.getMenu().performActions(optionChosen, bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    /* Used for demoing use of mock. Ignore */
    @Ignore
    public void testIfMock() throws IOException {
        InputOutputDevice device = mock(InputOutputDevice.class);
        final String userInputString = "My First Mock";
        BibliotecaApp app = new BibliotecaApp(device);

        when(device.readInput()).thenReturn(userInputString);

        app.getInputFromUserAndWriteSomething();

        verify(device).writeOutput(userInputString + " Appended");
    }
}
