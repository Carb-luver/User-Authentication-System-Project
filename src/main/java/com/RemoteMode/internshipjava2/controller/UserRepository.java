package com.RemoteMode.internshipjava2.controller;

import com.RemoteMode.internshipjava2.model.UserEntity;
import com.RemoteMode.internshipjava2.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findById(@Param("userid") long Id);

    public List<UserEntity> findByRole(@Param("role") UserRole role);

    public UserEntity findByEmail(@Param("email") String email);

}
