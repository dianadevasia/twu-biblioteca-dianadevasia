package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.view.IMenuAction;
import com.twu.biblioteca.view.MainMenu.Login;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;

import java.io.IOException;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class ReturnBook implements IMenuAction<Book> {

    Library<Book> bookLibrary;

    public ReturnBook(Library<Book> library){
        this.bookLibrary=library;
    }

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp)
    {
        Customer customer = (Customer) Login.getUser();
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        try
        {
            ioDevice.writeOutput("Enter the name of the item You want to return ");
            String bookToReturn = ioDevice.readInput();
            customer.returnItem(bookToReturn, bookLibrary);
            ioDevice.writeOutput("Thank you for returning.");
        }
        catch(BookNotValidException e)
        {
            ioDevice.writeOutput("That is not a valid book to return.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return BibliotecaApp.OutputStatus.CONTINUE;
    }

    public String getMenuName()
    {
        return "Return Book.";
    }
}
