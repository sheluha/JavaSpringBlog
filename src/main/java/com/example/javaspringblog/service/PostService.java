package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Post;

import java.util.List;

public interface PostService {

    public List<Post> getAllPosts();

    public void savePost(Post post);

    Post getPostById(int postId);
}
