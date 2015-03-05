package com.twu.biblioteca.error;

/**
 * Created by dianadevasia on 03/03/15.
 */
public class CustomerNotValidException extends Exception {
    String message;

    public CustomerNotValidException(String errorMessage) {
        this.message=errorMessage;
    }
}
