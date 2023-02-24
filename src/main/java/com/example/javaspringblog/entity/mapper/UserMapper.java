package com.example.javaspringblog.entity.mapper;

import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;

import java.time.LocalDate;

public interface UserMapper {
    static User fromCreateRequest(CreateUserRequest userRequest){
        User user = new User();
        user.setUserPassword(userRequest.getUserPassword());
        user.setUserName(userRequest.getUserName());
        user.setRole("USER");
        user.setRegisterDate(LocalDate.now());
        return user;
    }
}
