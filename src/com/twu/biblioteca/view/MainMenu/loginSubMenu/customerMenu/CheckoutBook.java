package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.IMenuAction;

import java.io.IOException;

/**
 * Created by dianadevasia on 02/03/15.
 */
public class CheckoutBook implements IMenuAction<Book> {

    Library<Book> bookLibrary;

    public CheckoutBook(Library<Book> bookLibrary){
        this.bookLibrary=bookLibrary;
    }

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp)
    {
        InputOutputDevice ioDevice=bibliotecaApp.getIoDevice();
        Customer customer = (Customer) Login.getUser();
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
        return BibliotecaApp.OutputStatus.CONTINUE;
    }

    public String getMenuName(){
        return "Checkout Book.";
    }
}
