package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        ArrayList<Book> bookList = createBookList();
        Library library = new Library(bookList);

        System.out.println("Welcome to The Bangalore Public Library");
        showMenu(library);

    }

    public static void showMenu(Library library) throws IOException
    {
        int optionChosen = 0;
        do {

            System.out.println("Choose the options to go forward");
            System.out.println("1. List Books");
            System.out.println("2. Quit");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            optionChosen = Integer.parseInt(br.readLine());

            performActions(optionChosen, library);

        } while (optionChosen != 2);
    }

    public static String performActions(int optionChosen,Library library){
        String str="";
        switch (optionChosen) {
            case 1:
                str="You choose option 1";
                library.printBookDetails();
                break;
            case 2:
                str = "You choose option 2";
                System.out.println("Exiting!!! ");
                break;

            default:
                str = "You have entered a wrong input.";
                System.out.println(str);
                System.out.println("Select a valid option from the menu list to go forward!\n");
                break;
        }
        return str;
    }

    public static ArrayList<Book> createBookList() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Learning Python", "Mark Lutz", 2009));
        bookList.add(new Book("HeadFirst Java", "Kathy Sierra", 2005));
        bookList.add(new Book("Test Driven Development", "Kent Beck", 2003));
        bookList.add(new Book("Java (TM) Design Patterns", "James W Cooper", 2000));
        return bookList;
    }
}
