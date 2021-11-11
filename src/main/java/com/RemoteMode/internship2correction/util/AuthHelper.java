package com.RemoteMode.internship2correction.util;

import com.RemoteMode.internship2correction.dto.LoginUserRequest;
import com.RemoteMode.internship2correction.dto.RegistrationUserRequest;
import com.RemoteMode.internship2correction.exception.BadRequestException;

public class AuthHelper {

    public void nullCheck(RegistrationUserRequest userRequest) {
        if (userRequest.getUserRole() == null || userRequest.getEmail() == null || userRequest.getFirstName() == null || userRequest.getLastName() == null || userRequest.getLogin() == null || userRequest.getPassword() == null) {
            throw new BadRequestException("Every field of UserEntity must be valid");
        }
        if (userRequest.getUserRole().toString() == " " || userRequest.getEmail().toString() == " " || userRequest.toString()==" " || userRequest.getLastName().toString()==" " || userRequest.getLogin().toString()==" " || userRequest.getPassword().toString()==" ") {
            throw new BadRequestException("Every field of UserEntity must be valid");
        }
    }

    public void loginNullCheck(LoginUserRequest loginUserRequest) {
        if (loginUserRequest.getLogin() == null || loginUserRequest.getPassword() == null) {
            throw new BadRequestException("Login and password must be valid.");
        }
        if (loginUserRequest.getLogin() ==" " || loginUserRequest.getPassword() ==" ") {
            throw new BadRequestException("Login and password must be valid.");
        }
    }
}
