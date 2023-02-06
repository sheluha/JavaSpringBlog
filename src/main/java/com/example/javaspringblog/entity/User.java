package com.example.javaspringblog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;


@Table(name = "users")
@Data
public class User {
    @Id
    @Column("user_id")
    int userId;

    @Column("user_name")
    String userName;

    @Column("user_password")
    String userPassword;

    @Column("is_admin")
    Boolean isAdmin;

    @Column("image_name")
    String imageName;
}
