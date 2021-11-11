package com.RemoteMode.internship2correction.controller;

import com.RemoteMode.internship2correction.exception.BadRequestException;
import com.RemoteMode.internship2correction.model.UserEntity;
import com.RemoteMode.internship2correction.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public void controller(long userId){
        if(userId == Long.parseLong(""))
            throw new BadRequestException("User ID must be valid.");
        Optional<UserEntity> userEntityListById= userService.findById(userId);
    }

    @GetMapping("/all")
    public ArrayList getUsers(){
        return userService.getUsers();
    }

}
