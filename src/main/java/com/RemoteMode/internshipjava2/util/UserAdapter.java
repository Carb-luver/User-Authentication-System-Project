package com.RemoteMode.internshipjava2.util;

import com.RemoteMode.internshipjava2.dto.RegistrationUserRequest;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.model.UserRole;
import org.apache.catalina.User;

public class UserAdapter {

    public static UserEntity toUserEntity(RegistrationUserRequest userRequest){
        UserEntity userEntity = new UserEntity(userRequest.getLogin(), userRequest.getPassword(), userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName(), UserRole.USER);
        return userEntity;
    }
}
