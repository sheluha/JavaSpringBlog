package com.example.javaspringblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "image_name")
    private String imageName;

    @JsonIgnore
    @Column(name = "role")
    private String role;

    @Column(name = "register_date")
    private LocalDate registerDate;
}
