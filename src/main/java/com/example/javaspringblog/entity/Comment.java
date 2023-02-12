package com.example.javaspringblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("comments")
@Data
@Builder
public class Comment {
    @Id
    private int commentId;

    private int postId;

    @JsonIgnore
    private int userId;

    private String commentBody;

    private String userName;

    private LocalDateTime createdAt;
}
