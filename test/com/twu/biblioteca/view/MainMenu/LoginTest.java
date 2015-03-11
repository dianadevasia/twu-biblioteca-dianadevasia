package com.twu.biblioteca.view.MainMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MockInputOutputDevice;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LoginTest {



    @Test
    public void testOnUnSuccessfuLogin() throws IOException {
        List<String> input=new ArrayList<String>();
        input.add("22-222");
        input.add("aaaa");
        input.add("n");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        Login menuAction = new Login();
        BibliotecaApp.OutputStatus actualOutput = menuAction.doAction(bibliotecaApp);
        BibliotecaApp.OutputStatus expectedOutput = BibliotecaApp.OutputStatus.CONTINUE;

        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    public void testExecutionOfLogoutOnSuccessfuLogin() throws IOException {
        List<String> input=new ArrayList<String>();
        input.add("111-1111");
        input.add("aaaa");
        input.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        Login menuAction = new Login();
        BibliotecaApp.OutputStatus actualOutput = menuAction.doAction(bibliotecaApp);
        BibliotecaApp.OutputStatus expectedOutput = BibliotecaApp.OutputStatus.CONTINUE;
        System.out.println(actualOutput);
        assertEquals(expectedOutput,actualOutput);

    }

    @Test
    public void testExecutionOfCheckoutBookOnSuccessfuLogin() throws IOException
    {

        List<String> input=new ArrayList<String>();
        input.add("111-1111");
        input.add("aaaa");
        input.add("3");
        input.add("1");
        input.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        BibliotecaApp.OutputStatus expected = BibliotecaApp.OutputStatus.CONTINUE;
        Login menuAction = new Login();
        BibliotecaApp.OutputStatus actualOutput = menuAction.doAction(bibliotecaApp);

        assertThat(actualOutput, is(expected));
    }

    @Test
    public void testExecutionOfReturnOfBookOnSuccessfuLogin() throws IOException
    {
        List<String> input=new ArrayList<String>();
        input.add("111-1111");
        input.add("aaaa");
        input.add("4");
        input.add("HeadFirst Java");
        input.add("1");
        MockInputOutputDevice ioDevice = new MockInputOutputDevice(input);
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);

        BibliotecaApp.OutputStatus expected = BibliotecaApp.OutputStatus.CONTINUE;
        Login menuAction = new Login();
        BibliotecaApp.OutputStatus actualOutput = menuAction.doAction(bibliotecaApp);

        assertThat(actualOutput, is(expected));
    }


}