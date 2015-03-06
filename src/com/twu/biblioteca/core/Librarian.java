package com.twu.biblioteca.core;

/**
 * Created by dianadevasia on 05/03/15.
 */
public class Librarian {
    static String  username;
    static String password;

    public Librarian(String username, String password) {
        Librarian.username = username;
        Librarian.password = password;
    }

    public static boolean validateLibrarian(String username,String password){
        if(Librarian.username.equals(username) && Librarian.password.equals(password))
            return true;
        return false;
    }


}
