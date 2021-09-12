package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.repository.UserRepository;
import com.RemoteMode.internshipjava2.dto.LoginUserRequest;
import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.util.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Service
@Transactional
public class AuthorizationService {

    private final UserRepository userRepository;
    UserAdapter userAdapter;

    @Autowired
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long registerUser(RegistrationUserRequest userRequest) {
        UserEntity userEntity = userAdapter.toUserEntity(userRequest);
        return userEntity.getUserId();
        userRepository.save(userEntity);
    }

    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @RequestMapping(value = "login")
    public void login(LoginUserRequest loginUserRequest){
        String login = loginUserRequest.getLogin();
        String password = loginUserRequest.getPassword();
        UserEntity userEntity = userRepository.findByLogin(login);
        if(userEntity.getPassword() == password){

        }
    }
}
