package com.example.clearsolution_testtask.exceptions;

public class InvalidDateRangeException extends Exception{
    private static final String EX_MESSAGE = "Invalid date range.";

    public InvalidDateRangeException(){
        super(EX_MESSAGE);
    }
}
