package com.example.javaspringblog.controller;


import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    boolean getNamePassword(@RequestBody User user){
        if(userService.findUser(user.getUserName(),user.getUserPassword())) {
            return true;
        }
        else{
            return false;
        }
    }
    @PostMapping("/signup")
    boolean signUp(@RequestBody User user){
        if(user.getUserName().length()>5 && user.getUserPassword().length()>5){
            userService.saveUser(user);
            return true;
        }
        else{
            return false;
        }

    }


}