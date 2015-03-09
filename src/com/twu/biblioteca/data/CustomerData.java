package com.twu.biblioteca.data;

import com.twu.biblioteca.core.Book;
import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;
import com.twu.biblioteca.core.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 09/03/15.
 */
public class CustomerData {
    private ArrayList<Customer> customerList = new ArrayList<Customer>();

    public CustomerData(Library<Book> bookLibrary,Library<Movie> movieLibrary){
        customerList.add(new Customer("111-1111", "aaaa","roo","roo@gmail.com",1111111111,bookLibrary,movieLibrary));
        customerList.add(new Customer("222-2222", "bbbb","koo","koo@gmail.com",1111111112,bookLibrary,movieLibrary));
        customerList.add(new Customer("333-3333", "cccc","foo","foo@gmail.com",1111111113,bookLibrary,movieLibrary));
        customerList.add(new Customer("444-4444", "dddd","poo","poo@gmail.com",1111111114,bookLibrary,movieLibrary));
    }

    public List<Customer> allCustomers(){
        return customerList;
    }
}
