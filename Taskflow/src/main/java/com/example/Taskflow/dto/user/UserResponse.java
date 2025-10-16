package com.example.Taskflow.dto.user;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class UserResponse {

    int id;
    String name;
    String email;
    String role;
    Timestamp registerDate;
}
