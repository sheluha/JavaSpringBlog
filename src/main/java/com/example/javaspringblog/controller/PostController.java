package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreatePostRequest;
import com.example.javaspringblog.entity.mapper.PostMapper;
import com.example.javaspringblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/post")
    public ResponseEntity<CreatePostRequest> createNewPost(@RequestBody CreatePostRequest postRequest, @AuthenticationPrincipal SecurityUser user){
        postService.savePost(PostMapper.fromCreateRequest(postRequest,user));
        return new ResponseEntity<>(postRequest, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public Post getPostById(@PathVariable int postId){
        return postService.getPostById(postId);
    }

    //Нужно ли вынести в сервис слой?
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Integer> deletePost(@PathVariable int postId, @AuthenticationPrincipal SecurityUser user){
        User authorOfPost = postService.getPostById(postId).getUser();
        if(authorOfPost.getUserId() == user.getUserId() || "ADMIN".equals(user.getUser().getRole())){
            postService.deletePost(postId);
            return new ResponseEntity<>(postId,HttpStatus.OK);
        }
        return new ResponseEntity<>(postId,HttpStatus.UNAUTHORIZED);
    }
}
