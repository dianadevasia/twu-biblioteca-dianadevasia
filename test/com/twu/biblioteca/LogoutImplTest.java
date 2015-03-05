package com.twu.biblioteca;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.ActionImplementations.LogoutImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class LogoutImplTest {
    @Test
    public void testDoMethod(){
        InputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        bibliotecaApp.setLoggedInCustomer(new Customer("111-1111","aaaa"));
        LogoutImpl logout = new LogoutImpl();

        logout.doAction(bibliotecaApp);

        assertEquals(bibliotecaApp.getLoggedInCustomer(),null);

    }
}
