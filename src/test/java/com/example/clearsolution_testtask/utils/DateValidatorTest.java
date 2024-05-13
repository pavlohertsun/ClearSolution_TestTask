package com.example.clearsolution_testtask.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {
    private DateValidator dateValidator = new DateValidator();
    @Test
    void dateRangeIsValidTest() {
        Assertions.assertEquals(true, dateValidator.dateRangeIsValid(LocalDate.parse("2000-01-01"), LocalDate.parse("2005-01-01")));
    }
    @Test
    void dateRangeIsNotValidTest() {
        Assertions.assertEquals(false, dateValidator.dateRangeIsValid(LocalDate.parse("2010-01-01"), LocalDate.parse("2005-01-01")));
    }
}