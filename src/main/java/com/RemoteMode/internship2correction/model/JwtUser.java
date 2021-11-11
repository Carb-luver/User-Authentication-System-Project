package com.RemoteMode.internship2correction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements Serializable {

    @Getter
    String login;

    @Getter
    String password;

    @Getter
    long userId;

    @Getter
    String stringRole;

}
