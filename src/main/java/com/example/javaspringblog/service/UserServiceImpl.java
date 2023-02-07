package com.example.javaspringblog.service;

import com.example.javaspringblog.dao.UserDAO;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;
import com.example.javaspringblog.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Override
    public void saveUser(CreateUserRequest userRequest) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userRequest.setUserPassword(encoder.encode(userRequest.getUserPassword()));
        User user = User.builder()
                .userPassword(userRequest.getUserPassword())
                .userName(userRequest.getUserName())
                .build();
        userDAO.save(user);
    }

    @Override
    public User findByName(String userName) {
        return userDAO.getUserByUserName(userName).orElseThrow(()->
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
