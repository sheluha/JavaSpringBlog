package com.example.javaspringblog.dao;

import com.example.javaspringblog.entity.Post;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO extends CrudRepository<Post,Integer> {

    @Query("select * from posts order by created_at desc")
    List<Post> getAllOrderByCreatedAtDesc();

}
