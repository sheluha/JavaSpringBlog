package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.CommentDAO;
import com.example.javaspringblog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDAO commentDAO;

    @Override
    public List<Comment> getAllByPostId(int postId) {
        return commentDAO.getAllByPostId(postId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentDAO.save(comment);
    }
}
