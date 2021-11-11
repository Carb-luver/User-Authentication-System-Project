package com.RemoteMode.internship2correction.controller;

import com.RemoteMode.internship2correction.dto.LoginUserRequest;
import com.RemoteMode.internship2correction.dto.RegistrationUserRequest;
import com.RemoteMode.internship2correction.service.AuthorizationService;
import com.RemoteMode.internship2correction.util.AuthHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthorizationService authorizationService;
    AuthHelper authHelper;

    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public void registerUser(RegistrationUserRequest registrationUserRequest){
        authHelper.nullCheck(registrationUserRequest);
        authorizationService.registerUser(registrationUserRequest);
    }

    @PostMapping("/login")
    public String post(LoginUserRequest loginUserRequest) throws JsonProcessingException {
        authHelper.loginNullCheck(loginUserRequest);
        return authorizationService.login(loginUserRequest);
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        authorizationService.logout(httpServletResponse, httpServletRequest);
    }

}
