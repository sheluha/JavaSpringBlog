package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {

    void saveUser(CreateUserRequest userRequest);

    User findByName(String userName);

    User findById(int id);

    Iterable<User> getAllUsers();

    void deleteUserById(int id) throws IOException;

    void storeFile(SecurityUser user, MultipartFile file) throws IOException;

}
