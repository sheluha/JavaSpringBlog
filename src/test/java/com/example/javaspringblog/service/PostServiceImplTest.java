package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.PostDAO;
import com.example.javaspringblog.entity.Post;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.exception.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    PostDAO postDAO;
    @InjectMocks
    PostServiceImpl postService;

    private static Post post;

    private static User user;

    @BeforeAll
    static void setup(){
        user = new User();
        user.setUserId(0);
        user.setUserName("test");
        user.setUserPassword("testpsw");
        user.setRegisterDate(LocalDate.now());
        user.setRole("ADMIN");
        user.setImageName("");

        post = new Post();
        post.setPostBody("");
        post.setPostHeader("");
        post.setPostId(1);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
    }


    @Test
    void getAllPostsShouldReturnListOfPosts() {
        when(postDAO.findAll()).thenReturn(List.of(post));
        assertNotNull(postService.getAllPosts(0));

    }

    @Test
    void getPostById() {
        when(postDAO.findById(user.getUserId())).thenReturn(Optional.of(post));
        assertNotNull(postService.getPostById(user.getUserId()));
        assertThrows(NoSuchElementException.class,()->postService.getPostById(1));

    }
}