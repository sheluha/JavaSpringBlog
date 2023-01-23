package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.CommentDAO;
import com.example.javaspringblog.entity.Comment;
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
    public void saveComment(Comment comment) {
        commentDAO.save(comment);
    }
}
