package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.exception.BadRequestException;
import com.RemoteMode.internshipjava2.jwt.JwtFilter;
import com.RemoteMode.internshipjava2.model.JwtUser;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.model.UserRole;
import com.RemoteMode.internshipjava2.repository.UserRepository;
import com.RemoteMode.internshipjava2.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    UserRepository userRepository;
    UserEntity userEntity;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

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
        if(this.userEntity.getRole() == UserRole.ADMIN)
            return userRepository.findAll();
        else
            logger.log(Level.INFO, "Admin access only.");
            return null;
    }

}
