package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    boolean getNamePassword(@RequestBody User user){
        return userService.findUser(user.getUserName(), user.getUserPassword());
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