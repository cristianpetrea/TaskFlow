package com.example.Taskflow.models.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Timestamp creation_date;
    private Date deadline;
}
