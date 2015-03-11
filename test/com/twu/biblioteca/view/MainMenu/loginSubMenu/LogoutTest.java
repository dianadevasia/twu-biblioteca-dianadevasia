package com.twu.biblioteca.view.MainMenu.loginSubMenu;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.view.MockInputOutputDevice;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.view.InputOutputDevice;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class LogoutTest {
    @Test
    public void testDoMethod(){
        InputOutputDevice ioDevice = new MockInputOutputDevice();
        BibliotecaApp bibliotecaApp = new BibliotecaApp(ioDevice);
        bibliotecaApp.setLoggedInCustomer(new Customer("111-1111","aaaa",null,null));
        Logout logout = new Logout();

        BibliotecaApp.OutputStatus outputValue = logout.doAction(bibliotecaApp);

        BibliotecaApp.OutputStatus expectedOutput = BibliotecaApp.OutputStatus.QUIT;
        assertEquals(Login.getUser(),null);
        assertEquals(outputValue, expectedOutput);

    }
}
