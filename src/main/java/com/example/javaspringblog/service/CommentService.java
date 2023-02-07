package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.entity.dto.CreateCommentRequest;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByPostId(int postId);

    void saveComment(CreateCommentRequest commentRequest, SecurityUser user);
}
