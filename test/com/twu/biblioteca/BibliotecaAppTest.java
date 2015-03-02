package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.twu.biblioteca.view.BibliotecaApp.getBookList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {

    @Test
    public void testShowMenu(){
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        String expected="1. List Books\n2. Checkout Books\n3. Return Books\n0. Quit";
        bibliotecaApp.showMenu();

        assertThat(ioDevice.getActualWrittenOutput(),is(expected));
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueZero() throws IOException
    {
        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        int optionChosen=0;
        String expected = "Exiting!!! ";
        bibliotecaApp.performActions(optionChosen, library, customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueTwo() throws IOException
    {
        MockInputOutputDevice ioDevice = new MockInputOutputDevice("2");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        int optionChosen=2;
        String expected = "Thank you! Enjoy the book.";
        bibliotecaApp.performActions(optionChosen, library, customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionExistsOnInputValueThree() throws IOException
    {
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        Customer customer = new Customer("diana");
        Book removedBook = booklist.remove(0);
        customer.checkOutBook(removedBook);

        MockInputOutputDevice ioDeviceForReturn = new MockInputOutputDevice("Learning Python");
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDeviceForReturn);

        int optionChosen=3;

        String expected = "Thank you for returning the book.";
        bibliotecaApp.performActions(optionChosen, library, customer);

        assertThat(ioDeviceForReturn.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionRerunsOnInvalidInputValue() throws IOException
    {

        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        Customer customer = new Customer("diana");
        ArrayList<Book> booklist = getBookList();
        Library library = new Library(booklist);

        int optionChosen=-1;
        String expected="You have entered a wrong input.\nSelect a valid option from the menu list to go forward!";
        bibliotecaApp.performActions(optionChosen, library,customer);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testIfMock() throws IOException {
        InputOutputDevice device = mock(InputOutputDevice.class);
        final String userInputString = "My First Mock";
        BibliotecaApp app = new BibliotecaApp(device);

        when(device.readInput()).thenReturn(userInputString);

        app.getInputFromUserAndWriteSomething();

        verify(device).writeOutput(userInputString + " Appended");
    }
}
