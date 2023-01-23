package com.example.javaspringblog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    int postId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "post_header")
    String postHeader;

    @Column(name = "post_body")
    String postBody;

    @Column(name = "user_name")
    String userName;
}
