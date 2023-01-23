package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    PostService postService;

    @CrossOrigin(origins = "http://localhost:3000")
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
