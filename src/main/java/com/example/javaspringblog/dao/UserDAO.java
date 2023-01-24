package com.example.javaspringblog.dao;

import com.example.javaspringblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {
    boolean existsByUserNameAndUserPassword(String userName, String password);

    Optional<User> findUsersByUserName(String username);

    User findUserByUserId(int id);

    @Transactional
    @Modifying
    @Query("update User u set u.imageName = :imageName where u.userId = :id")
    void updateUserImageName(@Param("id") int id, @Param("imageName") String imageName);

}
