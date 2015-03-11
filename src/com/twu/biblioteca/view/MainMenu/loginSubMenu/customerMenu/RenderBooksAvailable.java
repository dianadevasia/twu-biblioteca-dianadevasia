package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.IMenuAction;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class RenderBooksAvailable implements IMenuAction<Book> {

    Library<Book> bookLibrary;

    public RenderBooksAvailable(Library<Book> bookLibrary) {
        this.bookLibrary = bookLibrary;
    }

    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp) {
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
        return BibliotecaApp.OutputStatus.CONTINUE;
    }

    public String getMenuName() {
        return "Print Books Available.";
    }
}
