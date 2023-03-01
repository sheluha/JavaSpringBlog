package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.CommentDAO;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Comment saveComment(Comment comment) {
        return commentDAO.save(comment);
    }

    @Override
    public void deleteCommentById(int commentId) {
        commentDAO.deleteById(commentId);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentDAO.findById(commentId).orElseThrow(() ->
                new NoSuchElementException("No such comment with id = " + commentId));
    }
}
