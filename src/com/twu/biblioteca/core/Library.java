package com.twu.biblioteca.core;

import com.twu.biblioteca.error.BookNotValidException;
import com.twu.biblioteca.error.MovieNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dianadevasia on 25/02/15.
 */
public class Library <T extends Item>
{
    private List<T> listOfItemsPresentInLibrary = new ArrayList<T>();
    private Map<T,Customer> listOfItemsBorrowedWithCustomerInformation = new ConcurrentHashMap<T,Customer>();

    public Library(List<T> listOfItemsPresentInLibrary) {
        this.listOfItemsPresentInLibrary = listOfItemsPresentInLibrary;
    }

    public List<T> getListOfItemsPresentInLibrary() {
        return listOfItemsPresentInLibrary;
    }

    public void validateId(int optionChosen) {
        int limitCrossed = listOfItemsPresentInLibrary.size() + 1;
        if (optionChosen < 1 || optionChosen >= limitCrossed) {
            if (listOfItemsPresentInLibrary.get(0) instanceof Book)
                throw new BookNotValidException();
            else
                throw new MovieNotValidException();
        }
    }

    public Item removeItemFromList(int optionChosen) {
        validateId(optionChosen);
        T removedItem = listOfItemsPresentInLibrary.get(optionChosen - 1);
        listOfItemsPresentInLibrary.remove(optionChosen - 1);
//        listOfItemsBorrowedWithCustomerInformation.put(removedItem,customer);
        return removedItem;
    }



    public void addItemToRepository(T returnedItem) {
        listOfItemsPresentInLibrary.add(returnedItem);
    }

    public boolean hasItems()
    {
         return (listOfItemsPresentInLibrary.size()!=0);
    }
}
