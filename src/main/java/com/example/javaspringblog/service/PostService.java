package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    void savePost(Post post);

    Post getPostById(int postId);
}
