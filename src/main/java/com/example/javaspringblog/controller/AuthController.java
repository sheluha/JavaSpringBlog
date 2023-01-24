package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

//    @PostMapping("/login")
//    boolean getNamePassword(@RequestBody User user){
//        return userService.findUser(user.getUserName(), user.getUserPassword());
//    }
    @PostMapping("/signup")
    void signUp(@RequestBody User user){

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUserPassword(encoder.encode(user.getUserPassword()));

        userService.saveUser(user);

    }


}