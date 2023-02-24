package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Post;


public interface PostService {

    Iterable<Post> getAllPosts();

    void savePost(Post post);

    Post getPostById(int postId);
}
