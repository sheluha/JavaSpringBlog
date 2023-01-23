package com.example.javaspringblog.controller;


import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.service.ImageService;
import com.example.javaspringblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;


    @PostMapping("/user")
    void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping("/upload")
    void uploadImage(@RequestParam("userId") int id, @RequestParam("file") MultipartFile file) throws IOException{
        imageService.store(file);
        userService.updateUserImageName(id, file.getOriginalFilename());

    }

    @GetMapping("/getUserImage/{id}")
    Resource getUserImage(@PathVariable int id) throws IOException{
        User user = userService.findById(id);
        return imageService.loadAsResource(user.getImageName());
    }

    @PostMapping("/getId")
    int getUserId(@RequestBody User user){
        return userService.findByName(user.getUserName()).getUserId();

    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable int id){
        return userService.findById(id);

    }


}
