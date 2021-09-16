package com.RemoteMode.internshipjava2.util;

import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.model.JwtUser;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.model.UserRole;

public class UserAdapter {

    public static UserEntity toUserEntity(RegistrationUserRequest userRequest){
        UserEntity userEntity = new UserEntity(userRequest.getLogin(), userRequest.getPassword(), userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName(), UserRole.USER);
        return userEntity;
    }

    public static JwtUser userEntityToJwtUser(UserEntity userEntity){
        JwtUser jwtUser = new JwtUser(userEntity.getLogin(), userEntity.getPassword(), userEntity.getUserId(), userEntity.getRole().toString());
        return jwtUser;
    }
}
