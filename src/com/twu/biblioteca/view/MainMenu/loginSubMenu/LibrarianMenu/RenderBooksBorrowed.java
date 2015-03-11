package com.twu.biblioteca.view.MainMenu.loginSubMenu.LibrarianMenu;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.IMenuAction;

import java.util.Map;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class RenderBooksBorrowed implements IMenuAction {
    Library<Book> booklibrary;

    public RenderBooksBorrowed(Library<Book> booklibrary) {
        this.booklibrary = booklibrary;
    }


    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp) {

        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        if(!booklibrary.hasBorrowedListInformation()) {
            ioDevice.writeOutput("Sorry.. No books borrowed yet.");
        }

        if(booklibrary.hasBorrowedListInformation()){
            ioDevice.writeOutput("The list of items borrowed are:\n");

            ioDevice.writeOutput("|----------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-18s%-18s%-20s%-11s\n", "Item Name", "Customer Name", "Customer Email", "Customer Phone No.");
            ioDevice.writeOutput("|----------------------------------------------------------------------------------------|");
            for (Map.Entry<Book, Customer> each : booklibrary.getListOfItemsBorrowedWithCustomerInformation().entrySet())
            {
                ioDevice.writeOutput("|%-18s|%-18s|%-20s|%-11d|\n",each.getKey().getName(), each.getValue().getName(),each.getValue().getEmail(), each.getValue().getPhoneNo());
            }
            ioDevice.writeOutput("");
        }
        return BibliotecaApp.OutputStatus.CONTINUE;
    }

    @Override
    public String getMenuName() {
        return "Librarian View For Borrowed Books";
    }
}
