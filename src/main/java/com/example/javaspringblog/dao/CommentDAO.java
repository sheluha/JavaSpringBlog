package com.example.javaspringblog.dao;

import com.example.javaspringblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentDAO extends JpaRepository<Comment,Integer> {
    List<Comment> getAllByPostIdOrderByCreatedAtDesc(int postId);
}
