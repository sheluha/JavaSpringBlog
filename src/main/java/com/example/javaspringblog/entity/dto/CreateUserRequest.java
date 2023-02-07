package com.example.javaspringblog.entity.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String userName;

    private String userPassword;
}
