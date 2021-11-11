package com.RemoteMode.internship2correction.dto;

import com.RemoteMode.internship2correction.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationUserRequest {

    String login;
    String password;
    String email;
    String firstName;
    String lastName;
    UserRole userRole;

}
