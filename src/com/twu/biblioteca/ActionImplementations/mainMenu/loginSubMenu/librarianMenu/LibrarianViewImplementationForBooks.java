package com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.librarianMenu;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

import java.util.Map;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class LibrarianViewImplementationForBooks implements MenuAction {
    Library<Book> booklibrary;

    public LibrarianViewImplementationForBooks(Library<Book> booklibrary) {
        this.booklibrary = booklibrary;
    }


    @Override
    public int doAction(BibliotecaApp bibliotecaApp) {

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
        return 0;
    }

    @Override
    public String printMenu() {
        return "Librarian View For Borrowed Books";
    }
}
