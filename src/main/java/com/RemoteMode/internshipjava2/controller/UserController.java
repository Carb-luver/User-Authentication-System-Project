package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/{id}", produces = "application/json")
//    public UserEntity getUserById(@PathVariable long id){
//        return findById(id);
//    }
}
