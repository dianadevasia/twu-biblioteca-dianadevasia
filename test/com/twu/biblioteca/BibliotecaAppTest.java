package com.twu.biblioteca;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.data.SeedData;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;
import com.twu.biblioteca.view.ActionImplementations.CheckoutBookImpl;
import com.twu.biblioteca.view.ActionImplementations.CustomerLoginImpl;
import com.twu.biblioteca.view.ActionImplementations.QuitMenuActionImpl;
import com.twu.biblioteca.view.ActionImplementations.ReturnBookImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class BibliotecaAppTest {

//    @Test
//    public void testShowMenu(){
//        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
//        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
//
//        String expected="1. Login\n2. List Books\n3. Checkout Books\n4. Return Books\n5. List Movies\n6. Checkout Movie\n7. My Information\n0. Quit";
//        bibliotecaApp.showMenu();
//
//        assertThat(ioDevice.getActualWrittenOutput(),is(expected));
//    }

    @Test
    public void testValidatePerformActionExistsOnInputValueZero() throws IOException
    {
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);

        MockInputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        int optionChosen=0;

        MenuAction quitmenu=new QuitMenuActionImpl();
        List<MenuAction>menuActionList=new ArrayList<MenuAction>();
        menuActionList.add(quitmenu);
        bibliotecaApp.setMenu(menuActionList);

        Customer customer= bibliotecaApp.customerList.get(0);
        System.out.println(bibliotecaApp.getMenu());

        String expected = "Exiting!!! ";
        bibliotecaApp.getMenu().performActions(optionChosen, bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionExistsForCheckoutBook() throws IOException
    {
        ArrayList<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("111-1111");
        inputValuesToGiveToTest.add("aaaa");
        inputValuesToGiveToTest.add("2");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);
        int optionChosen=0;

        List<MenuAction>menuActionList=new ArrayList<MenuAction>();
        menuActionList.add(new CustomerLoginImpl(new CheckoutBookImpl(bookLibrary)));
        bibliotecaApp.setMenu(menuActionList);


        String expected = "Thank you! Enjoy the Book.";

        bibliotecaApp.getMenu().performActions(optionChosen, bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
    }

    @Test
    public void testValidatePerformActionExistsForReturnOfBook() throws IOException
    {
        List<String> inputValuesToGiveToTest = new ArrayList<String>();
        inputValuesToGiveToTest.add("111-1111");
        inputValuesToGiveToTest.add("aaaa");
        inputValuesToGiveToTest.add("Learning Python");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(inputValuesToGiveToTest);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        SeedData instance= new SeedData();
        List<Book> booklist = instance.allBooks();
        Library<Book> bookLibrary = new Library<Book>(booklist);
        Customer customer= bibliotecaApp.customerList.get(0);
        customer.checkOutItem(booklist.get(0));
        int optionChosen=0;

        List<MenuAction>menuActionList=new ArrayList<MenuAction>();
        menuActionList.add(new CustomerLoginImpl(new ReturnBookImpl(bookLibrary)));
        bibliotecaApp.setMenu(menuActionList);

        String expected = "Thank you for returning.";

        bibliotecaApp.getMenu().performActions(optionChosen, bibliotecaApp);

        assertThat(ioDevice.getActualWrittenOutput(), is(expected));
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

    @Test
    public void testCustomerLoginSuccess() throws IOException {
        List<String>input=new ArrayList<String>();
        input.add("111-1111");
        input.add("aaaa");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        String expected ="Logged In successfully!!!";
        MenuAction menuAction = new CustomerLoginImpl(new DummyTestAction());
        menuAction.doAction(bibliotecaApp);

        assertEquals(ioDevice.getActualWrittenOutput(),expected);
    }

    @Test
    public void testCustomerLoginFailure() throws IOException {
        List<String>input=new ArrayList<String>();
        input.add("111-1");
        input.add("aaaa");
        input.add("n");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        String expected="Do you want to try again? (y/Y)";

        MenuAction menuAction = new CustomerLoginImpl();
        menuAction.doAction(bibliotecaApp);

        assertEquals(ioDevice.getActualWrittenOutput(),expected);
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


//    @Test
//    public void dummy(){
//        box b=new box();
//        createchiller(b);
//
//        assertTrue(b.getC() != null);
//    }
//
//    void createchiller(box b){
//        b.setC(new chiller(5));
//        return;
//    }
//
//    class chiller{
//        int a;
//
//        public chiller(int a) {
//            this.a = a;
//        }
//    }
//
//    class box{
//        chiller c;
//
//        public chiller getC() {
//            return c;
//        }
//
//        public void setC(chiller c) {
//            this.c = c;
//        }
//    }


}
