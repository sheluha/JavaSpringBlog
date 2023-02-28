package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.CommentDAO;
import com.example.javaspringblog.entity.Comment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    CommentDAO commentDAO;

    @InjectMocks
    CommentServiceImpl commentService;


    private static Comment comment;


    @BeforeAll
    static void setUp(){
        comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        comment.setCommentId(0);
        comment.setPostId(1);
        comment.setCommentBody("");
    }

    @Test
    void getAllByPostIdShouldReturnPostOrEmptyList() {
        when(commentDAO.getAllByPostIdOrderByCreatedAtDesc(comment.getPostId())).thenReturn(List.of(comment));
        assertNotNull(commentService.getAllByPostId(comment.getPostId()));
        assertEquals(commentService.getAllByPostId(0), Collections.emptyList());

    }
}