package com.twu.biblioteca;

/**
 * Created by dianadevasia on 27/02/15.
 */
public class QuitMenuActionImpl implements MenuAction {
    @Override
    public void doAction(Library library, Customer customer, InputOutputDevice ioDevice) {
        ioDevice.writeOutput("Exiting!!! ");
    }
}
