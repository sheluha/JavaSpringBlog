package com.example.javaspringblog.dao;

import com.example.javaspringblog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO extends CrudRepository<Post,Integer> {

}
