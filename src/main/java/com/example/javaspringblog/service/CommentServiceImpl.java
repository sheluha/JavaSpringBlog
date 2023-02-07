package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.dao.CommentDAO;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.entity.dto.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentDAO commentDAO;

    @Override
    public List<Comment> getAllByPostId(int postId) {
        return commentDAO.getAllByPostId(postId);
    }

    @Override
    public void saveComment(CreateCommentRequest commentRequest, SecurityUser user) {
        Comment comment = Comment.builder()
                        .commentBody(commentRequest.getCommentBody())
                        .postId(commentRequest.getPostId())
                        .userId(user.getUserId())
                        .userName(user.getUsername())
                        .build();
        commentDAO.save(comment);
    }
}
