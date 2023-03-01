package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {

    void saveUser(User user);

    User findByName(String userName);

    User findById(int id);

    Iterable<User> getAllUsers();

    void deleteUserById(int id);

    void storeFile(SecurityUser user, MultipartFile file);

}
