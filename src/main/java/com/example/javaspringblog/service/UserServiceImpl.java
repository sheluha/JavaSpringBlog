package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.UserDAO;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public boolean findUser(@RequestBody String userName, String password) {
        return userDAO.existsByUserNameAndUserPassword(userName,password);
    }

    @Override
    public User findByName(String userName) {
        return userDAO.findUsersByUserName(userName).orElseThrow(()->
                new NoSuchElementException("No such user with name = " + userName));
    }

    @Override
    public User findById(int id) {
        return userDAO.findUserByUserId(id).orElseThrow(()->
                new NoSuchElementException("No such user with id = " + id));
    }

    @Override
    public void updateUserImageName(int id, String imageName) {
        userDAO.updateUserImageName(id,imageName);
    }
}
