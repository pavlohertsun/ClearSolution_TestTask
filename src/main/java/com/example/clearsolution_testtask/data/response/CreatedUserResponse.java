package com.example.clearsolution_testtask.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedUserResponse {
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
