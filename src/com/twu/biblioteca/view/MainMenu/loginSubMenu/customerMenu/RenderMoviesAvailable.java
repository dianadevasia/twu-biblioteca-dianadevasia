package com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu;

import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.IMenuAction;

/**
 * Created by dianadevasia on 02/03/15.
 */
public class RenderMoviesAvailable implements IMenuAction<Movie> {

    Library<Movie> movieLibrary;

    public RenderMoviesAvailable(Library<Movie> movieLibrary){
        this.movieLibrary=movieLibrary;
    }
    @Override
    public BibliotecaApp.OutputStatus doAction(BibliotecaApp bibliotecaApp)
    {
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();

        if(!movieLibrary.hasLstOfItemsPresentInLibrary())
        {
            ioDevice.writeOutput("Sorry.. No movies left to checkout.");
        }

        if(movieLibrary.hasLstOfItemsPresentInLibrary())
        {
            ioDevice.writeOutput("The list of books you can choose from are:\n");

            ioDevice.writeOutput("|-------------------------------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-10s%-30s%-30s%-20s%s\n", "Sr.No.", "Movie Name", "Movie Director", "Release Year", "Movie rating");
            ioDevice.writeOutput("|-------------------------------------------------------------------------------------------------------------|");
            for(Movie each : movieLibrary.getListOfItemsPresentInLibrary())
                ioDevice.writeOutput("|%-10d|%-30s|%-30s|%-20d|%d\n", each.getMovieId(), each.getName(), each.getDirector(), each.getMovieYear(), each.getMovieRating());
            ioDevice.writeOutput("");
        }
        return BibliotecaApp.OutputStatus.CONTINUE;
    }
    public String getMenuName(){
        return "Print Movies Available.";
    }
}
