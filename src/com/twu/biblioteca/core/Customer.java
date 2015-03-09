package com.twu.biblioteca.core;

import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.LogoutImpl;
import com.twu.biblioteca.ActionImplementations.mainMenu.loginSubMenu.customerMenu.*;
import com.twu.biblioteca.Main;
import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.view.Menu;
import com.twu.biblioteca.view.MenuAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Customer extends User
{
    public static Menu createCustomerMenu(Library bookLibrary,Library movieLibrary){
        List<MenuAction> customerMenus = new ArrayList<MenuAction>();
        customerMenus.add(new LogoutImpl());
        customerMenus.add(new PrintBookNamesMenuActionImpl(bookLibrary));
        customerMenus.add(new CheckoutBookImpl(bookLibrary));
        customerMenus.add(new ReturnBookImpl(bookLibrary));
        customerMenus.add(new PrintMoviesAvailableImpl(movieLibrary));
        customerMenus.add(new CheckoutMovieImpl(movieLibrary));
        customerMenus.add(new PrintCustomerDetailsImpl());
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

    public static Customer validateCustomer(String customerId, String customerPassword) {
        Customer validCustomer = null;
        for (Customer eachCustomer : Main.customerList) {
            if (eachCustomer.getAuthenticationValues().getUserId().equals(customerId) && eachCustomer.getAuthenticationValues().getPassword().equals(customerPassword)) {
                validCustomer = eachCustomer;
                break;
            }
        }
        return validCustomer;
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
