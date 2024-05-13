package com.example.clearsolution_testtask.controllers;

import com.example.clearsolution_testtask.data.dtos.UserDto;
import com.example.clearsolution_testtask.data.request.CreateUserRequest;
import com.example.clearsolution_testtask.data.request.UpdateUserRequest;
import com.example.clearsolution_testtask.data.response.CreatedUserResponse;
import com.example.clearsolution_testtask.data.response.UserResponse;
import com.example.clearsolution_testtask.services.UserService;
import com.example.clearsolution_testtask.utils.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    void getAllUsersTest() throws Exception {
        List<UserDto> userDtos = Arrays.asList(
                new UserDto("john@example.com", "John", "Doe", LocalDate.parse("2000-01-01"), "Lviv", "3809612354"),
                new UserDto("jane@example.com", "Jane", "Smith", LocalDate.parse("2000-01-01"), "Lviv", "3809612354")
        );

        when(userService.getAllUsers()).thenReturn(userDtos);

        when(userMapper.fromUserDtoToUserResponse(any(UserDto.class)))
                .thenAnswer(invocation -> {
                    UserDto userDto = invocation.getArgument(0);
                    return new UserResponse(userDto.getEmail(), userDto.getFirstName(), userDto.getLastName(), LocalDate.parse("2000-01-01"), "Lviv", "3809612354");
                });

        mvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"));
    }

    @Test
    void createUserTest() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("john@example.com");
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setBirthDate(LocalDate.parse("2000-01-01"));
        createUserRequest.setAddress("Lviv");
        createUserRequest.setPhoneNumber("380912345418");

        UserDto userDto = new UserDto("john@example.com", "John", "Doe", LocalDate.parse("2000-01-01"), "Lviv", "380912345418");
        CreatedUserResponse createdUserResponse = new CreatedUserResponse("John", "Doe");

        when(userMapper.fromCreteUserReqToUserDto(createUserRequest)).thenReturn(userDto);
        when(userService.createUser(userDto)).thenReturn(userDto);
        when(userMapper.fromUserDtoToCreatedUserResponse(userDto)).thenReturn(createdUserResponse);

        mvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createUserRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User: " + createdUserResponse + " created successfully."));
    }

    @Test
    void testUpdateAllUserFields() throws Exception {
        long userId = 4L;
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("johndoe1@example.com","John1","Doe1", LocalDate.parse("1997-03-09"),"Lviv","380964569812");
        UserDto updatedUserDto = new UserDto("johndoe1@example.com","John1","Doe1", LocalDate.parse("1997-03-09"),"Lviv","380964569812");

        when(userMapper.fromUpdatingUserReqToUserDto(updateUserRequest)).thenReturn(updatedUserDto);

        mvc.perform(put("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateUserRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User with id#" + userId + " updated successfully."));

        verify(userService).updateUser(userId, updatedUserDto);
    }

    @Test
    void testDeleteUserById() throws Exception {
        long userId = 1L;

        mvc.perform(delete("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User with id#" + userId + " deleted successfully."));

        verify(userService).deleteUser(userId);
    }

    private String asJsonString(final Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}