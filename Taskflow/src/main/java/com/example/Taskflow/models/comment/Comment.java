package com.example.Taskflow.models.comment;

import com.example.Taskflow.models.task.Task;
import com.example.Taskflow.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private String content;
    private Timestamp adding_date;
    private User user;
    private Task task;
}
