package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.view.IMenuAction;
import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Item;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class RenderCustomerDetails<T extends Item> implements IMenuAction<T> {

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp){
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        Customer customer = (Customer) Login.getUser();
        ioDevice.writeOutput("|-----------------------------|");
        ioDevice.writeOutput("%-15s%s\n", "|Name::",customer.getName() );
        ioDevice.writeOutput("%-15s%d\n", "|Contact No::", customer.getPhoneNo());
        ioDevice.writeOutput("%-15s%s\n", "|Email Id::", customer.getEmail());
        ioDevice.writeOutput("|-----------------------------|");
        return BibliotecaApp.OutputStatus.CONTINUE;
    }

    public String getMenuName()
    {
        return "My Information.";
    }
}
