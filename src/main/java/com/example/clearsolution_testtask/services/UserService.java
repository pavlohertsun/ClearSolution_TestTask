package com.example.clearsolution_testtask.services;

import com.example.clearsolution_testtask.data.dtos.UserDto;
import com.example.clearsolution_testtask.entities.User;
import com.example.clearsolution_testtask.exceptions.InvalidBirthDateException;
import com.example.clearsolution_testtask.exceptions.InvalidDateRangeException;
import com.example.clearsolution_testtask.exceptions.InvalidEmailException;
import com.example.clearsolution_testtask.exceptions.UserNotFoundException;
import com.example.clearsolution_testtask.repositories.UserRepository;
import com.example.clearsolution_testtask.utils.DateValidator;
import com.example.clearsolution_testtask.utils.UserMapper;
import com.example.clearsolution_testtask.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserValidator userValidator;
    private DateValidator dateValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, UserValidator userValidator, DateValidator dateValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
        this.dateValidator = dateValidator;
    }

    public UserDto createUser(UserDto userDto) throws InvalidEmailException, InvalidBirthDateException{
        if (!userValidator.creatingUserEmailIsValid(userDto.getEmail()))
            throw new InvalidEmailException();
        else if (!userValidator.creatingUserBirthDateIsValid(userDto.getBirthDate()))
            throw new InvalidBirthDateException();

        return userMapper.fromUserEntityToUserDto(userRepository.save(userMapper.fromUserDtoToUserEntity(userDto)));
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::fromUserEntityToUserDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getAllUsersInDateRange(LocalDate startDate, LocalDate endDate) throws InvalidDateRangeException{
        if (!dateValidator.dateRangeIsValid(startDate, endDate))
            throw new InvalidDateRangeException();

        return userRepository.findAllInDateRange(startDate, endDate).stream()
                .map(userMapper::fromUserEntityToUserDto)
                .collect(Collectors.toList());
    }
    public boolean updateUser(long id, UserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getBirthDate() != null) {
            user.setBirthDate(userDto.getBirthDate());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }
        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

        userRepository.save(user);

        return true;
    }

    public boolean deleteUser(long id) throws UserNotFoundException{
        if (!userRepository.existsById(id))
            throw new UserNotFoundException(String.valueOf(id));

        userRepository.deleteById(id);
        return true;
    }
}
