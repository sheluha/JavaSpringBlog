package com.example.javaspringblog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;


@Table("comments")
@Data
public class Comment {
    @Id
    @Column("comment_id")
    int commentId;

    @Column("post_id")
    int postId;

    @Column("user_id")
    int userId;

    @Column("comment_body")
    String commentBody;

    @Column("user_name")
    String userName;
}
