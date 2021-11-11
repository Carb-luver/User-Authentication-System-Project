package com.RemoteMode.internship2correction.service;

import com.RemoteMode.internship2correction.controller.UserRepository;
import com.RemoteMode.internship2correction.dto.LoginUserRequest;
import com.RemoteMode.internship2correction.dto.RegistrationUserRequest;
import com.RemoteMode.internship2correction.jwt.JwtProvider;
import com.RemoteMode.internship2correction.model.JwtUser;
import com.RemoteMode.internship2correction.model.UserEntity;
import com.RemoteMode.internship2correction.util.AuthHelper;
import com.RemoteMode.internship2correction.util.UserAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    UserAdapter userAdapter;
    UserEntity userEntity;
    AuthHelper authHelper;
    JwtUser jwtUser;
    JwtProvider jwtProvider;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegistrationUserRequest registrationUserRequest){
        userRepository.save(userAdapter.toUserEntity(registrationUserRequest));
    }

    public String login(LoginUserRequest loginUserRequest) throws JsonProcessingException {
        authHelper.loginNullCheck(loginUserRequest);
        jwtUser = UserAdapter.userEntityToJwtUser(userRepository.findByLoginAndPassword(loginUserRequest.getLogin(),loginUserRequest.getPassword()));
        return jwtProvider.generateToken(jwtUser);
    }

    public void logout(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        if(jwtProvider.getUrl(httpServletRequest).contains("/user") && jwtProvider.getUrl(httpServletRequest).contains("/logout"))
            jwtProvider.removeJwtUserFromCache(httpServletResponse);
    }

}
