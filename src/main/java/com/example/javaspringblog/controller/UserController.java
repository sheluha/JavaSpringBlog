package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;
import com.example.javaspringblog.service.ImageService;
import com.example.javaspringblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ImageService imageService;

    @PostMapping("/newuser")
    void signUp(@RequestBody CreateUserRequest userRequest){
        userService.saveUser(userRequest);
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable int id){
        return userService.findById(id);

    }

    @PostMapping("/upload")
    void uploadImage(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal SecurityUser user) throws IOException{
        imageService.store(file, user.getUserId());

    }

    @GetMapping("/getUserImage/{name}")
    Resource getUserImageByName(@PathVariable String name) throws IOException{
        User user = userService.findByName(name);
        return imageService.loadAsResource(user.getImageName());
    }

}
