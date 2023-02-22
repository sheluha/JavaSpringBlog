package com.example.javaspringblog.service;

import com.example.javaspringblog.config.SecurityUser;
import com.example.javaspringblog.dao.UserDAO;
import com.example.javaspringblog.entity.User;
import com.example.javaspringblog.entity.dto.CreateUserRequest;
import com.example.javaspringblog.exception.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    private final ImageService imageService;

    @Override
    public void saveUser(CreateUserRequest userRequest) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        userRequest.setUserPassword(encoder.encode(userRequest.getUserPassword()));
        User user = new User();
            user.setUserPassword(userRequest.getUserPassword());
            user.setUserName(userRequest.getUserName());
            user.setRole("USER");
            user.setRegisterDate(LocalDate.now());

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
    public Iterable<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void deleteUserById(int id) throws IOException {
        User user= userDAO.findUserByUserId(id).orElseThrow(()-> new NoSuchElementException("No such user with id:" + id));
        if("ADMIN".equals(user.getRole())){
            throw new RuntimeException("You cant delete Admin");
        }
        userDAO.deleteUserByUserId(id);
        imageService.deleteImage(user.getImageName());
    }

    @Override
    public void storeFile(SecurityUser user, MultipartFile file) throws IOException {
        if(user.getImageName() != null){
            imageService.deleteImage(user.getImageName());
        }
        String fileName = imageService.store(file);
        userDAO.updateUserImageName(user.getUserId(), fileName);
    }
}
