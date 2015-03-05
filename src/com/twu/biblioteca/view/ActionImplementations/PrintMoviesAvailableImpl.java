package com.twu.biblioteca.view.ActionImplementations;

import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;
import com.twu.biblioteca.view.BibliotecaApp;
import com.twu.biblioteca.view.InputOutputDevice;
import com.twu.biblioteca.view.MenuAction;

/**
 * Created by dianadevasia on 02/03/15.
 */
public class PrintMoviesAvailableImpl implements MenuAction<Movie> {

    Library<Movie> movieLibrary;

    public PrintMoviesAvailableImpl(Library<Movie> movieLibrary){
        this.movieLibrary=movieLibrary;
    }
    @Override
    public void doAction(BibliotecaApp bibliotecaApp){
        InputOutputDevice ioDevice = bibliotecaApp.getIoDevice();
        if(movieLibrary.getListOfItemsPresentInLibrary().size()!=0) {
            ioDevice.writeOutput("The list of books you can choose from are:\n");

            ioDevice.writeOutput("|-------------------------------------------------------------------------------------------------------------|");
            ioDevice.writeOutput("|%-10s%-30s%-30s%-20s%s\n", "Sr.No.", "Movie Name", "Movie Director", "Release Year", "Movie rating");
            ioDevice.writeOutput("|-------------------------------------------------------------------------------------------------------------|");
            for (int i = 0; i < movieLibrary.getListOfItemsPresentInLibrary().size(); i++)
                ioDevice.writeOutput("|%-10d|%-30s|%-30s|%-20d|%d\n", i + 1, movieLibrary.getListOfItemsPresentInLibrary().get(i).getName(), movieLibrary.getListOfItemsPresentInLibrary().get(i).getDirector(), movieLibrary.getListOfItemsPresentInLibrary().get(i).getMovieYear(), movieLibrary.getListOfItemsPresentInLibrary().get(i).getMovieRating());
            ioDevice.writeOutput("");
        }
        else
        {
            ioDevice.writeOutput("Sorry.. No movies left to checkout.");
        }
    }
    public String printMenu(){
        return "Print Movies Available.";
    }
}
