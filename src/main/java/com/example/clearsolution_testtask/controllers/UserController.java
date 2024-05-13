package com.example.clearsolution_testtask.controllers;

import com.example.clearsolution_testtask.data.dtos.UserDto;
import com.example.clearsolution_testtask.data.request.CreateUserRequest;
import com.example.clearsolution_testtask.data.request.UpdateUserRequest;
import com.example.clearsolution_testtask.data.response.CreatedUserResponse;
import com.example.clearsolution_testtask.data.response.UserResponse;
import com.example.clearsolution_testtask.exceptions.InvalidBirthDateException;
import com.example.clearsolution_testtask.exceptions.InvalidDateRangeException;
import com.example.clearsolution_testtask.exceptions.InvalidEmailException;
import com.example.clearsolution_testtask.exceptions.UserNotFoundException;
import com.example.clearsolution_testtask.services.UserService;
import com.example.clearsolution_testtask.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> resultList = userService.getAllUsers().stream()
                .map(userMapper::fromUserDtoToUserResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(resultList);
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest user) throws InvalidEmailException, InvalidBirthDateException {
        UserDto userDto = userService.createUser(userMapper.fromCreteUserReqToUserDto(user));

        CreatedUserResponse createdUserResponse = userMapper.fromUserDtoToCreatedUserResponse(userDto);

        return ResponseEntity.ok("User: " +  createdUserResponse + " created successfully.");
    }

    @GetMapping("/in-date-range")
    public ResponseEntity<List<UserResponse>> getAllUsersInDateRange(@Param(value = "fromDate") LocalDate fromDate, @Param(value = "toDate") LocalDate toDate)
            throws InvalidDateRangeException {
        List<UserResponse> resultList = userService.getAllUsersInDateRange(fromDate, toDate).stream()
                .map(userMapper::fromUserDtoToUserResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(resultList);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUserFields(@PathVariable long id, @RequestBody UpdateUserRequest user)
            throws UserNotFoundException {
        userService.updateUser(id, userMapper.fromUpdatingUserReqToUserDto(user));

        return ResponseEntity.ok("User with id#" + id + " updated successfully.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAllUserFields(@PathVariable long id, @RequestBody UpdateUserRequest user)
            throws UserNotFoundException {
        userService.updateUser(id, userMapper.fromUpdatingUserReqToUserDto(user));

        return ResponseEntity.ok("User with id#" + id + " updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) throws UserNotFoundException {
        userService.deleteUser(id);

        return ResponseEntity.ok("User with id#" + id + " deleted successfully.");
    }

}
