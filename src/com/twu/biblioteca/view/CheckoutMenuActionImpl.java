package com.twu.biblioteca.view;

import com.twu.biblioteca.core.BookNotValidException;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Book;

import java.io.IOException;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class CheckoutMenuActionImpl implements MenuAction {
    @Override
    public void doAction(Library library, Customer customer, InputOutputDevice ioDevice)
    {
        String bookId;
        ioDevice.writeOutput("Enter the book id you want to checkout from the following list of books.");
        boolean isNotValidBookId;
        try
        {
            do
            {
                bookId = ioDevice.readInput();
                isNotValidBookId = !bookId.matches("[0-9]+");
                if(isNotValidBookId)
                    ioDevice.writeOutput("Please enter a numeric book id value");
            }while(isNotValidBookId);

            int optionChosen = Integer.parseInt(bookId);

            Book removedBook = library.removeBookFromList(optionChosen);
            customer.checkOutBook(removedBook);
            ioDevice.writeOutput("Thank you! Enjoy the book.");
        }

        catch (BookNotValidException e)
        {
            String line = "That book is not available so select a different book or fix the spelling error.";
            ioDevice.writeOutput(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
