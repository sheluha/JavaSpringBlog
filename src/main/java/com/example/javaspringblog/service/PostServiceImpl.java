package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.dao.PostDAO;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.CreatePostRequest;
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
    public void savePost(CreatePostRequest postRequest, SecurityUser user) {
        Post post = Post.builder()
                .postBody(postRequest.getPostBody())
                .postHeader(postRequest.getPostHeader())
                .userName(user.getUsername())
                .userId(user.getUserId())
                .build();
        postDAO.save(post);
    }

    @Override
    public Post getPostById(int postId) {
        return postDAO.findById(postId).orElseThrow(() ->
             new NoSuchElementException("No such post with id = " + postId));
    }
}
