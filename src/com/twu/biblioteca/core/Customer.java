package com.twu.biblioteca.core;

import com.twu.biblioteca.view.MainMenu.loginSubMenu.Logout;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.view.MainMenu.loginSubMenu.customerMenu.*;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.IMenuAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Customer extends User
{
    public static Menu createCustomerMenu(Library bookLibrary,Library movieLibrary){
        List<IMenuAction> customerMenus = new ArrayList<IMenuAction>();
        customerMenus.add(new Logout());
        customerMenus.add(new RenderBooksAvailable(bookLibrary));
        customerMenus.add(new CheckoutBook(bookLibrary));
        customerMenus.add(new ReturnBook(bookLibrary));
        customerMenus.add(new RenderMoviesAvailable(movieLibrary));
        customerMenus.add(new CheckoutMovie(movieLibrary));
        customerMenus.add(new RenderCustomerDetails());
        Menu customerMenu = new Menu(customerMenus);
        return customerMenu;
    }

    private Authentication authentication;
    private String name;
    private String email;
    private int phoneNo;
    private List<Item> borrowedList = new ArrayList<Item>();

    public Customer(String userId,String password,String name,String email,int phoneNo,Library bookLibrary,Library movieLibrary) {
        super(createCustomerMenu(bookLibrary,movieLibrary));
        this.authentication=new Authentication(userId, password);
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Customer(String userId,String password,Library bookLibrary,Library movieLibrary) {
        super(createCustomerMenu(bookLibrary,movieLibrary));
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

    public void checkOutItem(int removedItemId, Library library) {
      Item removedItem = library.removeItemFromList(removedItemId,this);
        borrowedList.add(removedItem);
    }

    public void returnItem(String itemToRemove,Library library){

        Item returnedItem = returnValidItem(itemToRemove);
        library.addItemToRepository(returnedItem);
    }

    private Item returnValidItem(String itemToReturn)  {
        Item itemToRemove=null;
        for(Item each : borrowedList)
        {
            if(each.getName().equals(itemToReturn))
            {
                itemToRemove= each;
                break;
            }
        }
        if(itemToRemove==null) {
                throw new BookNotValidException();
        }
        borrowedList.remove(itemToRemove);
        return itemToRemove;
    }
}
