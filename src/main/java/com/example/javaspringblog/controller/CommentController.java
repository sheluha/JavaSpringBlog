package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.entity.dto.CreateCommentRequest;
import com.example.javaspringblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{postId}")
    List<Comment> showAllCommentsOnPost(@PathVariable int postId){
        return commentService.getAllByPostId(postId);
    }
    @PostMapping("/newcomment")
    void saveComment(@RequestBody CreateCommentRequest commentRequest, @AuthenticationPrincipal SecurityUser user){
        commentService.saveComment(commentRequest, user);
    }
}
