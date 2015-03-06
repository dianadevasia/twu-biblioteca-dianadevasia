package com.twu.biblioteca.ActionImplementations;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class PrintBookNamesMenuActionImpl implements MenuAction<Book> {

    Library<Book> bookLibrary;

    public PrintBookNamesMenuActionImpl(Library<Book> bookLibrary) {
        this.bookLibrary = bookLibrary;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp) {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();

        if (!bookLibrary.hasLstOfItemsPresentInLibrary())
            ioDevice.writeOutput("Sorry.. No books left to checkout.");

        if (bookLibrary.hasLstOfItemsPresentInLibrary()) {
            ioDevice.writeOutput("The list of books You can choose from are:\n");

            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-10s%-30s%-22s%s\n", "Book Id", "Book Name", "Book Author", "Publishing Year");
            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            for (Book each : bookLibrary.getListOfItemsPresentInLibrary()) {
                ioDevice.writeOutput("|%-10d|%-30s|%-30s|%-11s|\n", each.getBookId(), each.getName(), each.getAuthorName(), each.getYearOfPublishing());
            }
            ioDevice.writeOutput("");
        }
    }

    public String printMenu() {
        return "Print Books Available.";
    }
}
