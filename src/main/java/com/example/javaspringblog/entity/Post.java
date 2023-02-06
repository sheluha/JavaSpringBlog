package com.example.javaspringblog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "posts")
@Data
public class Post {

    @Id
    @Column("post_id")
    int postId;

    @Column("user_id")
    int userId;

    @Column("post_header")
    String postHeader;

    @Column("post_body")
    String postBody;

    @Column("user_name")
    String userName;
}
