package com.example.clearsolution_testtask.exceptions;

public class InvalidBirthDateException extends Exception {
    private static final String EX_MESSAGE = "Invalid birth date. You must be older than 18 years old.";
    public InvalidBirthDateException() {
        super(EX_MESSAGE);
    }
}

