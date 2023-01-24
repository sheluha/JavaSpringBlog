package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.UserDAO;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.exception.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
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
        user.setIsAdmin(false);
    }

    @Test
    void findUser() {
        when(userDAO.existsByUserNameAndUserPassword(user.getUserName(),user.getUserPassword())).thenReturn(true);
        assertTrue(userService.findUser(user.getUserName(),user.getUserPassword()));
    }

    @Test
    void findByName() {
        when(userDAO.findUsersByUserName(user.getUserName())).thenReturn(Optional.of(user));
        assertNotNull(userService.findByName("test"));

        when(userDAO.findUsersByUserName("")).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class,()-> userService.findByName(""));
    }

    @Test
    void findById() {
        when(userDAO.findUserByUserId(user.getUserId())).thenReturn(user);
        assertNotNull(userService.findById(user.getUserId()));

        when(userDAO.findUserByUserId(user.getUserId())).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class,()-> userService.findById(user.getUserId()));

    }
}