package com.example.javaspringblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


@Table(name = "users")
@Data
@Builder
public class User {

    @Id
    private int userId;

    private String userName;

    @JsonIgnore
    private String userPassword;

    private String imageName;

    @JsonIgnore
    private String role;

    private LocalDate registerDate;
}
