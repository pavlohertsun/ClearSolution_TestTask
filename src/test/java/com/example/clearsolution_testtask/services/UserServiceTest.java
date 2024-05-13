package com.example.clearsolution_testtask.services;

import com.example.clearsolution_testtask.data.dtos.UserDto;
import com.example.clearsolution_testtask.entities.User;
import com.example.clearsolution_testtask.exceptions.InvalidBirthDateException;
import com.example.clearsolution_testtask.exceptions.InvalidDateRangeException;
import com.example.clearsolution_testtask.exceptions.InvalidEmailException;
import com.example.clearsolution_testtask.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    void createUserTest() throws InvalidEmailException, InvalidBirthDateException {
        UserDto userDto = new UserDto("test@example.com", "Test", "Test", LocalDate.parse("2000-01-01"), "Test", "Test");

        UserDto actualDto = userService.createUser(userDto);

        Assertions.assertEquals(userDto, actualDto);
    }
    @Test
    void createUserWithInvalidEmailTest(){
        UserDto userDto = new UserDto("testExample.com", "Test", "Test", LocalDate.parse("2000-01-01"), "Test", "Test");

        Assertions.assertThrows(InvalidEmailException.class, () -> userService.createUser(userDto));
    }
    @Test
    void createUserWithInvalidBirthDateTest(){
        UserDto userDto = new UserDto("test@example.com", "Test", "Test", LocalDate.parse("2012-01-01"), "Test", "Test");

        Assertions.assertThrows(InvalidBirthDateException.class, () -> userService.createUser(userDto));
    }

    @Test
    void getAllUsersTest() {
        List<UserDto> expectedList = new ArrayList<>();

        expectedList.add(new UserDto("pavlo.hertsun@example.com", "Pavlo", "Hertsun",LocalDate.parse("2004-09-14"), "Vashynhtona 17St.", "380963528218"));
        expectedList.add(new UserDto("elom.bax@example.com", "Elom", "Bax", LocalDate.parse("1990-01-01"), "Cape town", "380991111111"));
        expectedList.add(new UserDto("bill.gates@example.com", "Bibl", "Gomes", LocalDate.parse("1996-01-21"), "New York", "380784561971"));
        expectedList.add(new UserDto("johndoe@example.com", "John", "Doe", LocalDate.parse("1997-03-09"), "Lviv", "380964569812"));


        List<UserDto> actualList = userService.getAllUsers();

        Assertions.assertIterableEquals(expectedList, actualList);
    }

    @Test
    void getAllUsersInDateRangeTest() throws InvalidDateRangeException {
        List<UserDto> expectedList = new ArrayList<>();

        expectedList.add(new UserDto("pavlo.hertsun@example.com", "Pavlo", "Hertsun",LocalDate.parse("2004-09-14"), "Vashynhtona 17St.", "380963528218"));
        expectedList.add(new UserDto("elom.bax@example.com", "Elom", "Bax", LocalDate.parse("1990-01-01"), "Cape town", "380991111111"));
        expectedList.add(new UserDto("bill.gates@example.com", "Bibl", "Gomes", LocalDate.parse("1996-01-21"), "New York", "380784561971"));
        expectedList.add(new UserDto("johndoe@example.com", "John", "Doe", LocalDate.parse("1997-03-09"), "Lviv", "380964569812"));

        List<UserDto> actualList = userService.getAllUsersInDateRange(LocalDate.parse("1990-01-01"), LocalDate.parse("2024-05-13"));

        Assertions.assertIterableEquals(expectedList, actualList);
    }

    @Test
    void getAllUsersInInvalidDateRangeTest() {
        List<UserDto> expectedList = new ArrayList<>();

        expectedList.add(new UserDto("pavlo.hertsun@example.com", "Pavlo", "Hertsun",LocalDate.parse("2004-09-14"), "Vashynhtona 17St.", "380963528218"));
        expectedList.add(new UserDto("elom.bax@example.com", "Elom", "Bax", LocalDate.parse("1990-01-01"), "Cape town", "380991111111"));
        expectedList.add(new UserDto("bill.gates@example.com", "Bibl", "Gomes", LocalDate.parse("1996-01-21"), "New York", "380784561971"));
        expectedList.add(new UserDto("johndoe@example.com", "John", "Doe", LocalDate.parse("1997-03-09"), "Lviv", "380964569812"));

        Assertions.assertThrows(InvalidDateRangeException.class, () -> userService.getAllUsersInDateRange(LocalDate.parse("2024-01-01"), LocalDate.parse("2004-05-13")));
    }
    @Test
    void updateUserTest() throws UserNotFoundException {
        UserDto userDto = new UserDto("testUpdated@example.com", "Test updated", "Test updated", LocalDate.parse("2000-01-01"), "Test", "Test");

        boolean actualResult = userService.updateUser(5, userDto);

        Assertions.assertEquals(true, actualResult);
    }

    @Test
    void updateNotExistingUserTest() {
        UserDto userDto = new UserDto("testUpdated@example.com", "Test updated", "Test updated", LocalDate.parse("2000-01-01"), "Test", "Test");

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.updateUser(10, userDto));
    }

    @Test
    void deleteUserTest() throws UserNotFoundException {
        boolean actualResult = userService.deleteUser(5);

        Assertions.assertEquals(true, actualResult);
    }
    @Test
    void deleteNonExistingUserTest() {
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(10));
    }
}