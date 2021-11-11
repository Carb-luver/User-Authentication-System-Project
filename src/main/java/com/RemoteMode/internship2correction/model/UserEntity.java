package com.RemoteMode.internship2correction.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="USERINFO")
public class UserEntity implements Serializable {

    @Id
    @Column(name="USERID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @NotNull
    long userId;

    @Getter
    @Setter
    @Column(name = "LOGIN")
    @NotNull
    String login;

    @Getter
    @Setter
    @Column(name = "PASSWORD")
    @NotNull
    String password;

    @Getter
    @Setter
    @Column(name = "EMAIL")
    @NotNull
    String email;

    @Getter
    @Setter
    @Column(name = "FIRSTNAME")
    @NotNull
    String firstName;

    @Getter
    @Setter
    @Column(name = "LASTNAME")
    @NotNull
    String lastName;

    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ROLE")
    @NotNull
    UserRole role;

    public UserEntity() {
    }

    public UserEntity(String login, String password, String email, String firstName, String lastName, UserRole role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
