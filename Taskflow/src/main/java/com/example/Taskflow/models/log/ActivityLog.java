package com.example.Taskflow.models.log;

import com.example.Taskflow.models.task.Task;
import com.example.Taskflow.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    private int id;
    private String action;
    private Timestamp action_date;
    private User user;
    private Task task;
}
