package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.exception.BadRequestException;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.repository.UserRepository;
import com.RemoteMode.internshipjava2.service.UserService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{userId}")
    public void controller(long userId){
        Long userIdObj = new Long(userId);
        if(userIdObj == null)
            throw new BadRequestException();
        UserEntity userEntity = userRepository.findById(userId);
        List<UserEntity> userEntityList = getUsers();
    }

    @RequestMapping(value = "/all")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

}
