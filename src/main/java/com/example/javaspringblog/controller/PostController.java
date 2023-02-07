package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.dto.CreatePostRequest;
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
    Iterable<Post> showAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/newpost")
    void createNewPost(@RequestBody CreatePostRequest postRequest, @AuthenticationPrincipal SecurityUser user){
        postService.savePost(postRequest,user);
    }

    @GetMapping("/post/{postId}")
    Post getPostById(@PathVariable int postId){
        return postService.getPostById(postId);
    }
}
