package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;
import com.example.javaspringblog.entity.mapper.UserMapper;
import com.example.javaspringblog.service.ImageService;
import com.example.javaspringblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/user")
    public ResponseEntity<CreateUserRequest> signUp(@RequestBody CreateUserRequest userRequest){
        userService.saveUser(UserMapper.fromCreateRequest(userRequest));
        return new ResponseEntity<>(userRequest, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<Boolean> signIn(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return userService.findById(id);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/upload")
    public ResponseEntity<MultipartFile> uploadImage(@RequestParam("file") MultipartFile file,@AuthenticationPrincipal SecurityUser user){
        userService.storeFile(user,file);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    @GetMapping("/image/{name}")
    public Resource getImageByName(@PathVariable String name){
        return imageService.loadAsResource(name);
    }

    @GetMapping("/getUserImage/{username}")
    public Resource getUserImageByName(@PathVariable String username){
        User user = userService.findByName(username);
        return imageService.loadAsResource(user.getImageName());
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}