package com.twu.biblioteca.core;

import com.twu.biblioteca.view.Menu;

/**
 * Created by dianadevasia on 06/03/15.
 */
public class User {
    public Menu roleAssociatedMenuList;

    public User(Menu roleAssociatedMenuList) {
        this.roleAssociatedMenuList = roleAssociatedMenuList;
    }

    public User() {

    }
}
