package com.example.javaspringblog.dao;


import com.example.javaspringblog.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {

    Optional<User> findUserByUserId(int id);

    @Transactional
    @Modifying
    @Query("update User u set u.imageName = :imageName where u.userId = :id")
    void updateUserImageName(@Param("id") int id, @Param("imageName") String imageName);

    @Transactional
    @Modifying
    @Query("update User u set u.userName = :userName where u.userId = :id")
    void updateUserName(@Param("id") int id, @Param("userName") String userName);

    @Transactional
    @Modifying
    @Query("update User u set u.userPassword = :userPassword where u.userId = :id")
    void updateUserPassword(@Param("id") int id, @Param("userPassword") String password);

    Optional<User> getUserByUserName(String userName);

    @Transactional
    @Modifying
    @Query("delete from User where userId = :id")
    void deleteUserByUserId(@Param("id") int id);

}
