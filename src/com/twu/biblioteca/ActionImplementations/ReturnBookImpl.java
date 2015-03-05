package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;
import java.io.IOException;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class ReturnBookImpl implements MenuAction<Book> {

    Library<Book> bookLibrary;

    public ReturnBookImpl(Library<Book> library){
        this.bookLibrary=library;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp)
    {
        Customer customer=bibliotecaApp.getLoggedInCustomer();
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        try
        {
            ioDevice.writeOutput("Enter the name of the item You want to return ");
            String bookToReturn = ioDevice.readInput();
            customer.returnItem(bookToReturn,bookLibrary);
            ioDevice.writeOutput("Thank you for returning.");
        }
        catch(BookNotValidException e)
        {
            ioDevice.writeOutput("That is not a valid book to return.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String printMenu()
    {
        return "Return Book.";
    }
}
