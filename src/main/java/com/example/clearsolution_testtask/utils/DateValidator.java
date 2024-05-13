package com.example.clearsolution_testtask.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidator {
    public boolean dateRangeIsValid(LocalDate startDate, LocalDate endDate){
        return startDate.isBefore(endDate);
    }
}
