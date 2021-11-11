package com.RemoteMode.internship2correction.service;

import com.RemoteMode.internship2correction.controller.UserRepository;
import com.RemoteMode.internship2correction.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findById(long userId){
        return userRepository.findById(userId);
    }

    public ArrayList getUsers(){
        return (ArrayList) userRepository.findAll();
    }
}
