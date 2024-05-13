package com.example.clearsolution_testtask.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator {
    @Value("${birthday.date.years}")
    private int minimumAge;

    public boolean creatingUserEmailIsValid(String email){
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean creatingUserBirthDateIsValid(LocalDate date){
        LocalDate currentDate = LocalDate.now();

        if(date.isAfter(currentDate)) return false;

        Period period = Period.between(date, currentDate);
        int years = period.getYears();
        return period.getYears() >= minimumAge;
    }
}
