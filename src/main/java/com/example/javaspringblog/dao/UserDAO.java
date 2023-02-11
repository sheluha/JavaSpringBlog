package com.example.javaspringblog.dao;


import com.example.javaspringblog.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User,Integer> {

    Optional<User> findUserByUserId(int id);

    @Modifying
    @Query("update users set image_name = :imageName where user_id = :id")
    void updateUserImageName(@Param("id") int id, @Param("imageName") String imageName);

    Optional<User> getUserByUserName(String userName);

    @Modifying
    @Query("delete from users where user_id = :id")
    void deleteUserByUserId(@Param("id") int id);

}
