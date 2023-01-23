package com.example.javaspringblog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "user_password")
    String userPassword;

    @Column(name = "is_admin")
    Boolean isAdmin;

    @Column(name = "image_name")
    String imageName;
}
