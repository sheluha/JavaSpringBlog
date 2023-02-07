package com.example.javaspringblog.entity.dto;

import lombok.Data;

@Data
public class CreateCommentRequest {

    private int postId;

    private String commentBody;
}
