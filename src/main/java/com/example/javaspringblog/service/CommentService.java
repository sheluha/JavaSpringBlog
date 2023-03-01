package com.example.javaspringblog.service;

import com.example.javaspringblog.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByPostId(int postId);

    Comment saveComment(Comment comment);

    void deleteCommentById(int commentId);

    Comment getCommentById(int commentId);
}
