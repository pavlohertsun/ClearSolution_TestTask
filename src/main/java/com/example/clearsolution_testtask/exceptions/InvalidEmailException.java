package com.example.clearsolution_testtask.exceptions;

public class InvalidEmailException extends Exception {
    private static final String EX_MESSAGE = "Invalid email format. Please enter it in the format example@example.com.";
    public InvalidEmailException() {
        super(EX_MESSAGE);
    }
}
