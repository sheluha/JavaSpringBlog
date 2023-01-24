package com.example.javaspringblog.dao;

import com.example.javaspringblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post,Integer> {

}
