package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{postId}")
    List<Comment> showAllComment(@PathVariable int postId){
        return commentService.getAllByPostId(postId);
    }
    @PostMapping("/comment")
    boolean saveComment(@RequestBody Comment comment){
        commentService.saveComment(comment);
        return true;
    }
}
