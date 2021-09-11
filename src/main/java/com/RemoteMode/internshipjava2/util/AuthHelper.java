package com.RemoteMode.internshipjava2.util;

import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.exception.BadRequestException;

public class AuthHelper {

    public void nullCheck(RegistrationUserRequest userRequest) {
        if (userRequest.getRole() == null || userRequest.getEmail() == null || userRequest.getFirstName() == null || userRequest.getLastName() == null || userRequest.getLogin() == null || userRequest.getPassword() == null) {
            throw new BadRequestException();
        }
        if (userRequest.getRole().toString()==" " || userRequest.getEmail().toString()==" " || userRequest.toString()==" " || userRequest.getLastName().toString()==" " || userRequest.getLogin().toString()==" " || userRequest.getPassword().toString()==" ") {
            throw new BadRequestException();
        }
    }
}
