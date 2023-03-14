package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.PostResponse;


public interface PostService {

    Iterable<PostResponse> getAllPosts(int pageNum);

    void savePost(Post post);

    Post getPostById(int postId);

    void deletePost(int postId);
}
