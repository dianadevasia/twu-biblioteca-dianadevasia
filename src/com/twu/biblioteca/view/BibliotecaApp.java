package com.twu.biblioteca.view;

import com.twu.biblioteca.core.Customer;
import com.twu.biblioteca.error.InvalidMenuOptionChoosen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BibliotecaApp
{
    public static List<Customer> customerList = new ArrayList<Customer>();
    private InputOutputDevice ioDevice;
    public Menu menu;
    private Customer loggedInCustomer=null;

    static{
    customerList.add(new Customer("111-1111", "aaaa","roo","roo@gmail.com",1111111111));
    customerList.add(new Customer("222-2222", "bbbb","koo","koo@gmail.com",1111111112));
    customerList.add(new Customer("333-3333", "cccc","foo","foo@gmail.com",1111111113));
    customerList.add(new Customer("444-4444", "dddd","poo","poo@gmail.com",1111111114));
    }

    public BibliotecaApp(InputOutputDevice ioDevice)
    {
        this.ioDevice = ioDevice;
    }

    public void setIoDevice(InputOutputDevice ioDevice) {
        this.ioDevice = ioDevice;
    }

    public Menu getMenu() {
        return menu;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public InputOutputDevice getIoDevice() {
        return ioDevice;
    }

    public void setLoggedInCustomer(Customer loggedInCustomer) {
        this.loggedInCustomer = loggedInCustomer;
    }

    public void setMenu(List<MenuAction> menu) {
        this.menu = new Menu(menu);
    }

    private int readInteger() throws IOException
    {
        return ioDevice.readInt("Please enter a numeric value for menu id.");
    }

    public void welcomeMessage() {
        ioDevice.writeOutput("Welcome to The Bangalore Public Library");
    }


    public int getMenuCodeForQuitMenu(Map<Integer, MenuAction> menuActionHashMap) {
        for (Map.Entry<Integer, MenuAction> entry : menuActionHashMap.entrySet()) {
            if (entry.getValue().equals("new QuitMenuActionImpl()")) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public void menuProcessing() throws IOException
    {
        int quitMenuCode = getMenuCodeForQuitMenu(menu.menuItems);
        int optionChosenForMenuSelection;
        do {
            menu.showMenu(ioDevice);

            optionChosenForMenuSelection = readInteger();

            try {
                menu.performActions(optionChosenForMenuSelection,this);
            }
            catch (InvalidMenuOptionChoosen e){
                ioDevice.writeOutput("You have entered a wrong input.");
                ioDevice.writeOutput("Select a valid option from the menu list to go forward!");
            }

        } while (optionChosenForMenuSelection != quitMenuCode);
    }

    // use for mock. //
    public void getInputFromUserAndWriteSomething() throws IOException {
        String input = ioDevice.readInput();
        ioDevice.writeOutput(input + " Appended");
    }
}
