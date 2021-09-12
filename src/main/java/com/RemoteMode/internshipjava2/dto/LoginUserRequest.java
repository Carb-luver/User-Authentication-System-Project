package com.RemoteMode.internshipjava2.dto;

import lombok.Getter;

public class LoginUserRequest {

    @Getter
    String login;

    @Getter
    String password;

    public LoginUserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
