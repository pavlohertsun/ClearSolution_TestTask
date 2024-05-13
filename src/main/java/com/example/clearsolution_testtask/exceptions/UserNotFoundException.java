package com.example.clearsolution_testtask.exceptions;

public class UserNotFoundException extends Exception {
    private static final String EX_MESSAGE = "User with id: %s not found";
    public UserNotFoundException(String id) {
        super(String.format(EX_MESSAGE, id));
    }
}
