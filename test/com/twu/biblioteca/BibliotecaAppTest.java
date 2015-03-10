package com.twu.biblioteca;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.QuitMenuActionImpl;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.MenuItemGenerator;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;
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

        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);

        List<Movie> movielist = instance.allMovies();
        Library<Movie> movieLibrary = new Library<Movie>(movielist);


        bibliotecaApp.menu=new Menu(MenuItemGenerator.createMenu(bookLibrary, movieLibrary));

        String expected="2\tQuit";
        bibliotecaApp.menu.showMenu(bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(),is(expected));
    }



    @Test
    public void testValidatePerformActionForCheckoutBook() throws IOException
    {

        List<String> input=new ArrayList<String>();
        input.add("111-1111");
        input.add("aaaa");
        input.add("3");
        input.add("1");
        input.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        int expected =0;
        LoginImpl menuAction = new LoginImpl();
        int actualOutput = menuAction.doAction(bibliotecaApp);

        assertThat(actualOutput, is(expected));
    }

    @Test
    public void testValidatePerformActionForReturnOfBook() throws IOException
    {
        List<String> input=new ArrayList<String>();
            input.add("111-1111");
            input.add("aaaa");
            input.add("4");
            input.add("HeadFirst Java");
            input.add("1");
            MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
            BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

            int expected =0;
            LoginImpl menuAction = new LoginImpl();
            int actualOutput = menuAction.doAction(bibliotecaApp);

            assertThat(actualOutput, is(expected));
    }

    @Test(expected = InvalidMenuOptionChoosen.class)
    public void testValidatePerformActionRerunsOnInvalidInputValue() throws IOException
    {
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        MenuAction quitmenu=new QuitMenuActionImpl();
        List<MenuAction>menuActionList=new ArrayList<MenuAction>();
        menuActionList.add(quitmenu);
        bibliotecaApp.setMenu(menuActionList);

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
