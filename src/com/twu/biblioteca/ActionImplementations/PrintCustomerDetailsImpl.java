package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Item;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 04/03/15.
 */
public class PrintCustomerDetailsImpl<T extends Item> implements MenuAction<T> {

    @Override
    public void doAction(BibliotecaApp bibliotecaApp){
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        Customer customer = bibliotecaApp.getLoggedInCustomer();
        ioDevice.writeOutput("|-----------------------------|");
        ioDevice.writeOutput("%-15s%s\n", "|Name::",customer.getName() );
        ioDevice.writeOutput("%-15s%d\n", "|Contact No::", customer.getPhoneNo());
        ioDevice.writeOutput("%-15s%s\n", "|Email Id::", customer.getEmail());
        ioDevice.writeOutput("|-----------------------------|");
    }

    public String printMenu()
    {
        return "My Information.";
    }
}
