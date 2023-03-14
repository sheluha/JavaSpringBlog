package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.PostDAO;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.PostResponse;
import com.example.javaspringblog.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostDAO postDAO;

    private final CommentService commentService;

    @Override
    public Iterable<PostResponse> getAllPosts(int pageNum) {
        return postDAO.findAll(PageRequest.of(pageNum,5))
                .map((post)->
                        new PostResponse(post,commentService.countCommentsByPostId(post.getPostId())));
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
