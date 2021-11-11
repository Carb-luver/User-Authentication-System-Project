package com.RemoteMode.internship2correction.util;

import com.RemoteMode.internship2correction.dto.RegistrationUserRequest;
import com.RemoteMode.internship2correction.model.JwtUser;
import com.RemoteMode.internship2correction.model.UserEntity;
import com.RemoteMode.internship2correction.model.UserRole;

public class UserAdapter {

    public UserEntity toUserEntity(RegistrationUserRequest registrationUserRequest){
        return new UserEntity(registrationUserRequest.getLogin(), registrationUserRequest.getPassword(), registrationUserRequest.getEmail(), registrationUserRequest.getFirstName(), registrationUserRequest.getLastName(), UserRole.USER);
    }

    public static JwtUser userEntityToJwtUser(UserEntity userEntity){
        return new JwtUser(userEntity.getLogin(), userEntity.getPassword(), userEntity.getUserId(), String.valueOf(userEntity.getRole()));
    }

}