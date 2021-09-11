package com.RemoteMode.internshipjava2.dto;

import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class RegistrationUserRequest {

    @Getter
    String login;

    @Getter
    String password;

    @Getter
    String email;

    @Getter
    String firstName;

    @Getter
    String lastName;

    @Getter
    UserRole role;

    @Getter
    UserEntity userEntity;


}
