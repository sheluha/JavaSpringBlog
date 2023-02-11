package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;


public interface UserService {

    void saveUser(CreateUserRequest userRequest);

    User findByName(String userName);

    User findById(int id);

    void updateUserImageName(int id, String imageName);

    Iterable<User> getAllUsers();

    void deleteUserById(int id);

}
