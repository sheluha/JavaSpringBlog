package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.User;

import java.util.Optional;


public interface UserService {

    User saveUser(User user);

    boolean findUser(String userName, String password);

    User findByName(String userName);

    User findById(int id);

    void updateUserImageName(int id, String imageName);


}
