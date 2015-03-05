package com.twu.biblioteca.core;

import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.error.MovieNotValidException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Customer
{
    private Authentication authentication;
    private String name;
    private String email;
    private int phoneNo;
    private List<Item> borrowedList = new ArrayList<Item>();

    public Customer(String userId,String password,String name,String email,int phoneNo) {
        this.authentication=new Authentication(userId, password);
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }
    public Customer(String userId,String password) {
        this.authentication=new Authentication(userId, password);
        this.name="-";
        this.email="-";
        this.phoneNo=0;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public List<Item> getBorrowedList() {
        return borrowedList;
    }

    public Authentication getAuthenticationValues() {
        return authentication;
    }

    public void checkOutItem(Item removedItem) {
        borrowedList.add(removedItem);
    }

    public Item returnItem(String itemToRemove){

        Item returnedItem = returnValidItem(itemToRemove);

        return returnedItem;
    }

    public Item returnValidItem(String itemToReturn)  {
        int i;
        Item itemToRemove=null;
        for(i=0;i< borrowedList.size();i++)
        {
            Item t = getBorrowedList().get(i);
            if(t.getName().equals(itemToReturn))
            {
                itemToRemove= borrowedList.remove(i);
                break;
            }
        }
        if(itemToRemove==null) {
            if (borrowedList.get(0) instanceof Book)
                throw new BookNotValidException();
            else
                throw new MovieNotValidException();
        }
        return itemToRemove;
    }
}
