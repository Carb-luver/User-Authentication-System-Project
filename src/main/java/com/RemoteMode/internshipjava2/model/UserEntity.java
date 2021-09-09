package com.RemoteMode.internshipjava2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USERINFO")
public class UserEntity implements Serializable {

    @Id
    @Column(name="USERID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    long userId;

    @Getter
    @Setter
    @Column(name = "LOGIN")
    String login;

    @Getter
    @Setter
    @Column(name = "PASSWORD")
    String password;

    @Getter
    @Setter
    @Column(name = "EMAIL")
    String email;

    @Getter
    @Setter
    @Column(name = "FIRSTNAME")
    String firstName;

    @Getter
    @Setter
    @Column(name = "LASTNAME")
    String lastName;

    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ROLE")
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
