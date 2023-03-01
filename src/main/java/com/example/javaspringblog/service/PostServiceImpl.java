package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.PostDAO;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostDAO postDAO;

    @Override
    public Iterable<Post> getAllPosts() {
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

    @Override
    public void deletePost(int postId) {
        postDAO.deleteById(postId);
    }
}
