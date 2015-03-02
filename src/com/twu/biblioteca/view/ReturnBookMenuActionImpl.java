package com.twu.biblioteca.view;

import com.twu.biblioteca.core.BookNotValidException;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Library;

import java.io.IOException;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class ReturnBookMenuActionImpl implements MenuAction {
    @Override
    public void doAction(Library library, Customer customer, InputOutputDevice ioDevice) {
        try
        {
            ioDevice.writeOutput("Enter the book name You want to return ");
            String bookToReturn = ioDevice.readInput();
            Book returnedBook = customer.returnBook(bookToReturn);
            library.addBookToRepository(returnedBook);
            ioDevice.writeOutput("Thank you for returning the book.");
        }
        catch(BookNotValidException e)
        {
            ioDevice.writeOutput("That is not a valid book to return.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
