package com.example.Taskflow.dto.project.output;

import lombok.Value;

import java.sql.Timestamp;

@Value
public class ProjectResponse {

    int id;
    String name;
    String description;
    Timestamp creation_date;
    int user_id;
}
