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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        user.setRegisterDate(LocalDate.now());
        user.setRole("ADMIN");
        user.setImageName("");
    }

    @Test
    void findByNameShouldReturnUserOrThrowException() {
        when(userDAO.getUserByUserName(user.getUserName())).thenReturn(Optional.of(user));
        assertNotNull(userService.findByName("test"));
        assertThrows(NoSuchElementException.class,()-> userService.findByName(""));
    }

    @Test
    void findByIdShouldReturnUserOrThrowException() {
        when(userDAO.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
        assertNotNull(userService.findById(user.getUserId()));
        assertThrows(NoSuchElementException.class,()-> userService.findById(1));

    }

    @Test
    void deleteUserByIdShouldThrowExceptionIfAdmin() {
        when(userDAO.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
        assertThrows(RuntimeException.class,()->userService.deleteUserById(user.getUserId()));
    }

}
