package com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Item;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class PrintCustomerDetailsImpl<T extends Item> implements MenuAction<T> {

    @Override
    public int doAction(BibliotecaApp bibliotecaApp){
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        Customer customer = (Customer) LoginImpl.getUser();
        ioDevice.writeOutput("|-----------------------------|");
        ioDevice.writeOutput("%-15s%s\n", "|Name::",customer.getName() );
        ioDevice.writeOutput("%-15s%d\n", "|Contact No::", customer.getPhoneNo());
        ioDevice.writeOutput("%-15s%s\n", "|Email Id::", customer.getEmail());
        ioDevice.writeOutput("|-----------------------------|");
        return 0;
    }

    public String printMenu()
    {
        return "My Information.";
    }
}
