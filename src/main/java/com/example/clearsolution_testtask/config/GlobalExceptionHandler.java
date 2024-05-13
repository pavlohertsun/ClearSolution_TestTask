package com.example.clearsolution_testtask.config;

import com.example.clearsolution_testtask.exceptions.InvalidBirthDateException;
import com.example.clearsolution_testtask.exceptions.InvalidDateRangeException;
import com.example.clearsolution_testtask.exceptions.InvalidEmailException;
import com.example.clearsolution_testtask.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidBirthDateException.class})
    public ResponseEntity<Map<String, List<String>>> invalidBirthDateException(InvalidBirthDateException ex) {
        return getErrorsMap(ex, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {InvalidEmailException.class})
    public ResponseEntity<Map<String, List<String>>> invalidEmailException(InvalidEmailException ex) {
        return getErrorsMap(ex, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Map<String, List<String>>> userNotFoundException(UserNotFoundException ex) {
        return getErrorsMap(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidDateRangeException.class})
    public ResponseEntity<Map<String, List<String>>> invalidDateRangeException(InvalidDateRangeException ex) {
        return getErrorsMap(ex, HttpStatus.I_AM_A_TEAPOT);
    }


//    private Map<String, Map<String, List<String>>> getErrorsMap(Map<String, List<String>> errors) {
//        Map<String, Map<String, List<String>>> errorResponse = new HashMap<>();
//        errorResponse.put("errors", errors);
//        return errorResponse;
//    }

    private ResponseEntity<Map<String, List<String>>> getErrorsMap(Throwable ex, HttpStatus status) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(map, new HttpHeaders(), status);
    }
}
