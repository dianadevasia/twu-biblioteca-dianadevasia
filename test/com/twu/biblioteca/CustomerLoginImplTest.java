package com.twu.biblioteca;

import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.ActionImplementations.CustomerLoginImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerLoginImplTest {

    @Test
    public void testPrintMenu() throws Exception
    {
        InputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        CustomerLoginImpl customerLoginImpl = new CustomerLoginImpl(new DummyTestAction());
        String expected = "dummy string";
        String actual = customerLoginImpl.printMenu();

        assertEquals(actual,expected);
    }
}