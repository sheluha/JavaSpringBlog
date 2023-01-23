package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.User;

import java.util.List;

public interface UserService {


    public User saveUser(User user);

    public boolean findUser(String userName, String password);

    public User findByName(String userName);

    public User findById(int id);

    public void updateUserImageName(int id, String imageName);


}
