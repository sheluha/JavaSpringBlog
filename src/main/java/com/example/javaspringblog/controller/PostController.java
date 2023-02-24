package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.CreatePostRequest;
import com.example.javaspringblog.entity.mapper.PostMapper;
import com.example.javaspringblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public Iterable<Post> showAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/newpost")
    public void createNewPost(@RequestBody CreatePostRequest postRequest, @AuthenticationPrincipal SecurityUser user){
        postService.savePost(PostMapper.fromCreateRequest(postRequest,user));
    }

    @GetMapping("/post/{postId}")
    public Post getPostById(@PathVariable int postId){
        return postService.getPostById(postId);
    }
}
