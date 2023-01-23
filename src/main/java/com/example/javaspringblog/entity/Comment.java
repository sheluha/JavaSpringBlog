package com.example.javaspringblog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    int commentId;

    @Column(name = "post_id")
    int postId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "comment_body")
    String commentBody;

    @Column(name = "user_name")
    String userName;
}
