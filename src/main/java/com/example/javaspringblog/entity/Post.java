package com.example.javaspringblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "posts")
@Data
@Builder
public class Post {

    @Id
    private int postId;

    @JsonIgnore
    private int userId;

    private String postHeader;

    private String postBody;

    private String userName;
}
