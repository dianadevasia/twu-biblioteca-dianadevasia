package com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.ActionImplementations.mainMenu.LoginImpl;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 02/03/15.
 */
public class CheckoutBookImpl implements MenuAction<Book> {

    Library<Book> bookLibrary;

    public CheckoutBookImpl(Library<Book> bookLibrary){
        this.bookLibrary=bookLibrary;
    }

    @Override
    public int doAction(BibliotecaApp bibliotecaApp)
    {
        InputOutputDevice ioDevice=bibliotecaApp.getIoDevice();
        Customer customer = (Customer)LoginImpl.getUser();
        ioDevice.writeOutput("Enter the Book id you want to checkout from the above list of Books.");
        try
        {
            int bookId = ioDevice.readInt("Please enter a numeric book id value");
            customer.checkOutItem(bookId, bookLibrary);
            ioDevice.writeOutput("Thank you! Enjoy the Book.");
        }

        catch (BookNotValidException e)
        {
            String line = "That Book is not available so select a different book or fix the spelling error.";
            ioDevice.writeOutput(line);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String printMenu(){
        return "Checkout Book.";
    }
}
