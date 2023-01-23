package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.PostDAO;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.exception.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostDAO postDAO;

    @Override
    public List<Post> getAllPosts() {
        return postDAO.findAll();
    }

    @Override
    public void savePost(Post post) {
        postDAO.save(post);
    }

    @Override
    public Post getPostById(int postId) {
        return postDAO.findById(postId).orElseThrow(() ->
             new NoSuchElementException("No such post with id = " + postId));
    }
}
