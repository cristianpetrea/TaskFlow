package com.example.Taskflow.models.user;

import lombok.*;

import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Timestamp register_date;
}
