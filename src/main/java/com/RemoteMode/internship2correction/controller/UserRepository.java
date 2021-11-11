package com.RemoteMode.internship2correction.controller;

import com.RemoteMode.internship2correction.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByLoginAndPassword(@Param("login") String login, @Param("password") String password);



}
