package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getAllByPostId(int postId);

    public void saveComment(Comment comment);
}
