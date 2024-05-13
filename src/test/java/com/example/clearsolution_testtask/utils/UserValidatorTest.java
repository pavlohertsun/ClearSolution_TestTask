package com.example.clearsolution_testtask.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    private UserValidator userValidator = new UserValidator();
    @Test
    void creatingUserEmailIsValidTest() {
        Assertions.assertEquals(true, userValidator.creatingUserEmailIsValid("example@example.com"));
    }
    @Test
    void creatingUserEmailIsNotValidTest() {
        Assertions.assertEquals(false, userValidator.creatingUserEmailIsValid("exampleexample.com"));
    }

    @Test
    void creatingUserBirthDateIsValidTest() {
        Assertions.assertEquals(true, userValidator.creatingUserBirthDateIsValid(LocalDate.parse("2004-09-14")));
    }
    @Test
    void creatingUserBirthDateIsNotValidTest() {
        Assertions.assertEquals(false, userValidator.creatingUserBirthDateIsValid(LocalDate.parse("2012-09-14")));
    }
}