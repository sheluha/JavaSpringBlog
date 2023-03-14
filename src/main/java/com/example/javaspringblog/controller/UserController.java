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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity<String> signIn(@AuthenticationPrincipal SecurityUser user){
        return new ResponseEntity<>(user.getUser().getRole(), HttpStatus.OK);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<User> getUserById(@AuthenticationPrincipal SecurityUser user){
        return new ResponseEntity<>(user.getUser(),HttpStatus.OK);
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
    public Resource getImageByImageName(@PathVariable String name){
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
    @PutMapping("/user")
    public ResponseEntity<Integer> updateUserName(@RequestBody CreateUserRequest userRequest, @AuthenticationPrincipal SecurityUser user){
        if(!userRequest.getUserName().isBlank()){
            userService.updateUserName(user.getUserId(), userRequest.getUserName());
        }
        if(!userRequest.getUserPassword().isBlank()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(userRequest.getUserPassword());
            userService.updateUserPassword(user.getUserId(),password);
        }
        return new ResponseEntity<>(user.getUserId(),HttpStatus.OK);
    }

}