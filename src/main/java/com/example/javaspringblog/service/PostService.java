package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.CreatePostRequest;


public interface PostService {

    Iterable<Post> getAllPosts();

    void savePost(CreatePostRequest postRequest, SecurityUser user);

    Post getPostById(int postId);
}
