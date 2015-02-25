package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by dianadevasia on 25/02/15.
 */
class Library
{
    ArrayList<Book> bookList;

    public Library(ArrayList<Book> bookList)
    {
        this.bookList = bookList;
    }

    public void printBookDetails()
    {
        System.out.println("|--------------------------------------------------------------------------|");
        System.out.println("\t\t\t\t\t\t\t");
        System.out.printf("%-30s%-30s%-30s\n","Book Name","Book Author","Publishing Year");

        System.out.println("|---------------------------------------------------------------------------");
        for(int i=0;i<bookList.size();i++)
        {
            System.out.printf("|%-30s|%-30s|%-12s|\n",bookList.get(i).bookName,bookList.get(i).authorName,bookList.get(i).yearOfPublishing);
        }
    }

}
