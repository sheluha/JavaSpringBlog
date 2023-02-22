package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.UserDAO;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.exception.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplIntTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    private static final User user = new User();

    @BeforeAll
    public static void setUp(){
        user.setUserId(0);
        user.setUserName("test");
        user.setUserPassword("testpsw");
        user.setImageName("");
    }

    @Test
    void findByName() {
        when(userDAO.getUserByUserName(user.getUserName())).thenReturn(Optional.of(user));
        assertNotNull(userService.findByName("test"));

        when(userDAO.getUserByUserName("")).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class,()-> userService.findByName(""));
    }

    @Test
    void findById() {
        when(userDAO.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
        assertNotNull(userService.findById(user.getUserId()));

        when(userDAO.findUserByUserId(user.getUserId())).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class,()-> userService.findById(user.getUserId()));

    }
}
