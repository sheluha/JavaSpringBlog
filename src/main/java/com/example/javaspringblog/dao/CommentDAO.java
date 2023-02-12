package com.example.javaspringblog.dao;

import com.example.javaspringblog.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends CrudRepository<Comment,Integer> {
    List<Comment> getAllByPostIdOrderByCreatedAtDesc(int postId);
}
