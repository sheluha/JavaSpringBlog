package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByPostId(int postId);

    void saveComment(Comment comment);
}
