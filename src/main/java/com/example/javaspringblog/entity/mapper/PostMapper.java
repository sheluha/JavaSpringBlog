package com.example.javaspringblog.entity.mapper;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.CreatePostRequest;

import java.time.LocalDateTime;

public interface PostMapper {

    static Post fromCreateRequest(CreatePostRequest postRequest, SecurityUser user) {
        Post post = new Post();
        post.setPostHeader(postRequest.getPostHeader());
        post.setPostBody(postRequest.getPostBody());
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user.getUser());
        return post;
    }

}