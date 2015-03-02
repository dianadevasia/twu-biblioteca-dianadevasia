package com.twu.biblioteca.view;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class PrintBookMenuActionImpl implements MenuAction {
    @Override
    public void doAction(Library library, Customer customer, InputOutputDevice ioDevice)
    {
        if(library.getBookList().size()!=0) {
            ioDevice.writeOutput("The list of books You can choose from are:\n");

            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-10s%-30s%-22s%s\n", "Book Id", "Book Name", "Book Author", "Publishing Year");
            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            for (int i = 0; i < library.getBookList().size(); i++) {
                ioDevice.writeOutput("|%-10d|%-30s|%-30s|%-11s|\n", i + 1, library.getBookList().get(i).getBookName(), library.getBookList().get(i).getAuthorName(), library.getBookList().get(i).getYearOfPublishing());
            }
            ioDevice.writeOutput("");

        }
        else
        {
            ioDevice.writeOutput("Sorry.. No books left to checkout.");
        }
    }
}
