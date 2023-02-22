package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.dao.CommentDAO;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.entity.dto.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentDAO commentDAO;

    @Override
    public List<Comment> getAllByPostId(int postId) {
        return commentDAO.getAllByPostIdOrderByCreatedAtDesc(postId);
    }

    @Override
    public void saveComment(CreateCommentRequest commentRequest, SecurityUser user) {
        Comment comment = new Comment();
            comment.setPostId(commentRequest.getPostId());
            comment.setCommentBody(commentRequest.getCommentBody());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUser(user.getUser());

        commentDAO.save(comment);
    }
}
