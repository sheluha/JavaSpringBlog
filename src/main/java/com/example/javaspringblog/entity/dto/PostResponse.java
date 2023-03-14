package com.example.javaspringblog.entity.dto;

import com.example.javaspringblog.entity.Post;
import lombok.Data;

@Data
public class PostResponse {

    private Post post;

    int commentsCount;

    public PostResponse(Post post,int commentsCount) {
        this.post = post;
        this.commentsCount = commentsCount;
    }
}
