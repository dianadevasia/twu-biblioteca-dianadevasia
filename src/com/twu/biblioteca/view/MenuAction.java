package com.twu.biblioteca.view;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.core.Library;

/**
 * Created by dianadevasia on 27/02/15.
 */
public interface MenuAction {

    public void doAction(Library library, Customer customer, InputOutputDevice ioDevice);
}
