package com.example.javaspringblog.entity.dto;

import lombok.Data;

@Data
public class CreatePostRequest {

    private String postHeader;

    private String postBody;
}
