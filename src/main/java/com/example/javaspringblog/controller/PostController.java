package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    List<Post> showAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/newpost")
    void savePost(@RequestBody Post post){
        postService.savePost(post);
    }

    @GetMapping("/post/{postId}")
    Post getPost(@PathVariable int postId){
        return postService.getPostById(postId);
    }
}
