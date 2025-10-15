package com.example.Taskflow.models.project;

import com.example.Taskflow.models.user.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private int id;
    private String name;
    private String description;
    private Timestamp creation_date;
    private User user;
}
