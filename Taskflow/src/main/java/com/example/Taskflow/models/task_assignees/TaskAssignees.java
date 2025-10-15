package com.example.Taskflow.models.task_assignees;

import com.example.Taskflow.models.task.Task;
import com.example.Taskflow.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignees {
    private Task task;
    private User user;
}
