package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.dto.LoginUserRequest;
import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.service.*;
import com.RemoteMode.internshipjava2.util.AuthHelper;
import com.RemoteMode.internshipjava2.util.UserAdapter;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthorizationService authorizationService;
    AuthHelper authHelper;
    UserAdapter userAdapter;
    LoginUserRequest loginUserRequest;

    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public long registerUser(RegistrationUserRequest userRequest){
        long userId = authorizationService.registerUser(userRequest);
        return userId;
    }

    public void nullCheck(RegistrationUserRequest userRequest){
        authHelper.nullCheck(userRequest);
    }

    @PostMapping("/login")
    public void post(LoginUserRequest loginUserRequest) throws Exception {
        String login = loginUserRequest.getLogin();
        String password = loginUserRequest.getPassword();
        authHelper.loginNullCheck(login, password);
        authorizationService.login(loginUserRequest);
    }
}
