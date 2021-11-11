package com.RemoteMode.internship2correction.dto;

import lombok.Getter;

@Getter
public class LoginUserRequest {
    String login;
    String password;

    public LoginUserRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
