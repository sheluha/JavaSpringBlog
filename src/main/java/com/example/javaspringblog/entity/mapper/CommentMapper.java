package com.example.javaspringblog.entity.mapper;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.entity.dto.CreateCommentRequest;

import java.time.LocalDateTime;

public interface CommentMapper {
    static Comment fromCreateRequest(CreateCommentRequest commentRequest, SecurityUser user){
        Comment comment = new Comment();
        comment.setPostId(commentRequest.getPostId());
        comment.setCommentBody(commentRequest.getCommentBody());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user.getUser());
        return comment;
    }

}
