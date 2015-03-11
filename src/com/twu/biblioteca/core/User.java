package com.twu.biblioteca.core;

import com.twu.biblioteca.view.Menu;

import java.util.List;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class User {
    public Menu roleAssociatedMenuList;

    public User(Menu roleAssociatedMenuList) {
        this.roleAssociatedMenuList = roleAssociatedMenuList;
    }

    public static User validateUser(String userId, String userPassword, List<Librarian> librarianList, List<Customer> customerList){
        User validUser = null;
        for (Librarian eachLibrarian : librarianList) {
            if (eachLibrarian.getAuthenticationValues().getUserId().equals(userId) && eachLibrarian.getAuthenticationValues().getPassword().equals(userPassword)) {
                validUser = eachLibrarian;
                return validUser;
            }
        }

        for (Customer eachCustomer : customerList) {
            if (eachCustomer.getAuthenticationValues().getUserId().equals(userId) && eachCustomer.getAuthenticationValues().getPassword().equals(userPassword)) {
                validUser = eachCustomer;
                return validUser;
            }
        }
        return validUser;

    }

}
