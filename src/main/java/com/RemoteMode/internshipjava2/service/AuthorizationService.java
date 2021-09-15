package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.exception.BadRequestException;
import com.RemoteMode.internshipjava2.model.JwtUser;
import com.RemoteMode.internshipjava2.repository.UserRepository;
import com.RemoteMode.internshipjava2.dto.LoginUserRequest;
import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.util.UserAdapter;
import jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;


@Service
@Transactional
public class AuthorizationService {

    private final UserRepository userRepository;
    UserAdapter userAdapter;
    JwtProvider jwtProvider;
    JwtTokenCacheService jwtTokenCacheService;

    @Autowired
    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public AuthorizationService(UserRepository userRepository, JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public long registerUser(RegistrationUserRequest userRequest) {
        UserEntity userEntity = userAdapter.toUserEntity(userRequest);
        userRepository.save(userEntity);
        return userEntity.getUserId();
    }

    @RequestMapping(value = "login")
    public String login(LoginUserRequest loginUserRequest) throws Exception {
        String login = loginUserRequest.getLogin();
        String password = loginUserRequest.getPassword();
        UserEntity userEntity = userRepository.findUserByLoginAndPassword(login, password);
        if(userEntity==null){
            throw new BadRequestException("Incorrect username or password.");
        }
        JwtUser jwtUser = userAdapter.userEntityToJwtUser(userEntity);
        String jwtToken = jwtTokenCacheService.addToken(jwtUser);
        return jwtToken;
    }
}
