package com.twu.biblioteca.core;

/**
 * Created by dianadevasia on 03/03/15.
 */
public class Authentication {
    private String userId;
    private String password;

    Authentication(String userId,String password){
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
