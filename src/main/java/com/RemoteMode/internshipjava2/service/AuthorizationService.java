package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.controller.UserRepository;
import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.util.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorizationService {

    private final UserRepository userRepository;
    UserAdapter userAdapter;

    @Autowired
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long registerUser(RegistrationUserRequest userRequest){
        UserEntity userEntity = userAdapter.toUserEntity(userRequest);
        return userEntity.getUserId();
        userRepository.save(userEntity);
    }
}
