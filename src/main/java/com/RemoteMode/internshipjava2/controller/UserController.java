package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.exception.BadRequestException;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.repository.UserRepository;
import com.RemoteMode.internshipjava2.service.UserService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/{userId}")
    public void controller(long userId){
        Long userIdObj = new Long(userId);
        if(userIdObj == null)
            throw new BadRequestException("User ID must be valid.");
        UserEntity userEntity = userRepository.findById(userId);
        List<UserEntity> userEntityList = getUsers();
    }

    @PostMapping(value = "/all")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

}
