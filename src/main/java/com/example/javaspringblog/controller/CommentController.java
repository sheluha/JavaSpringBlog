package com.example.javaspringblog.controller;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.entity.Comment;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateCommentRequest;
import com.example.javaspringblog.entity.mapper.CommentMapper;
import com.example.javaspringblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{postId}")
    public List<Comment> showAllCommentsOnPost(@PathVariable int postId){
        return commentService.getAllByPostId(postId);
    }
    @PostMapping("/comment")
    public ResponseEntity<Comment> saveComment(@RequestBody CreateCommentRequest commentRequest, @AuthenticationPrincipal SecurityUser user){
        Comment comment = commentService.saveComment(CommentMapper.fromCreateRequest(commentRequest,user));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // Нужно ли вынести в сервис слой?
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Integer> deleteCommentById(@PathVariable int commentId, @AuthenticationPrincipal SecurityUser user){
        User authorOfComment = commentService.getCommentById(commentId).getUser();
        if(authorOfComment.getUserId() == user.getUserId() || "ADMIN".equals(user.getUser().getRole())){
            commentService.deleteCommentById(commentId);
            return new ResponseEntity<>(commentId,HttpStatus.OK);
        }
        return new ResponseEntity<>(commentId,HttpStatus.UNAUTHORIZED);
    }

}
