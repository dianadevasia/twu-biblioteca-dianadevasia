package com.twu.biblioteca.view.ActionImplementations;

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

    public PrintBookNamesMenuActionImpl(Library<Book> bookLibrary){
        this.bookLibrary=bookLibrary;
    }

    @Override
    public void doAction(BibliotecaApp bibliotecaApp)
    {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        if(bookLibrary.getListOfItemsPresentInLibrary().size()!=0) {
            ioDevice.writeOutput("The list of books You can choose from are:\n");

            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-10s%-30s%-22s%s\n", "Book Id", "Book Name", "Book Author", "Publishing Year");
            ioDevice.writeOutput("|------------------------------------------------------------------------------------|");
            for (int i = 0; i < bookLibrary.getListOfItemsPresentInLibrary().size(); i++) {
                ioDevice.writeOutput("|%-10d|%-30s|%-30s|%-11s|\n", i + 1, bookLibrary.getListOfItemsPresentInLibrary().get(i).getName(), bookLibrary.getListOfItemsPresentInLibrary().get(i).getAuthorName(), bookLibrary.getListOfItemsPresentInLibrary().get(i).getYearOfPublishing());
            }
            ioDevice.writeOutput("");
        }
        else
        {
            ioDevice.writeOutput("Sorry.. No books left to checkout.");
        }
    }

    public String printMenu()
    {
        return "Print Books Available.";
    }
}
