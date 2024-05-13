package com.example.clearsolution_testtask.utils;

import com.example.clearsolution_testtask.data.dtos.UserDto;
import com.example.clearsolution_testtask.data.request.CreateUserRequest;
import com.example.clearsolution_testtask.data.request.UpdateUserRequest;
import com.example.clearsolution_testtask.data.response.CreatedUserResponse;
import com.example.clearsolution_testtask.data.response.UserResponse;
import com.example.clearsolution_testtask.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto fromCreteUserReqToUserDto(CreateUserRequest user){
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }
    public User fromUserDtoToUserEntity(UserDto userDto){
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }
    public User fromUserDtoToUserEntity(Long id ,UserDto userDto){
        User user = new User();

        user.setId(id);
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }

    public UserDto fromUserEntityToUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    public CreatedUserResponse fromUserDtoToCreatedUserResponse(UserDto userDto){
        CreatedUserResponse createdUserResponse = new CreatedUserResponse();

        createdUserResponse.setFirstName(userDto.getFirstName());
        createdUserResponse.setLastName(userDto.getLastName());

        return createdUserResponse;
    }

    public UserResponse fromUserDtoToUserResponse(UserDto userDto){
        UserResponse user = new UserResponse();

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }
    public UserDto fromUpdatingUserReqToUserDto(UpdateUserRequest userRequest){
        UserDto userDto = new UserDto();

        userDto.setEmail(userRequest.getEmail());
        userDto.setFirstName(userRequest.getFirstName());
        userDto.setLastName(userRequest.getLastName());
        userDto.setBirthDate(userRequest.getBirthDate());
        userDto.setAddress(userRequest.getAddress());
        userDto.setPhoneNumber(userRequest.getPhoneNumber());

        return userDto;
    }
}
