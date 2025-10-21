package com.example.Taskflow.dto.task.output;

import lombok.Value;

import java.sql.Date;
import java.sql.Timestamp;

@Value
public class TaskResponse {
    int id;
    String title;
    String description;
    String status;
    String priority;
    Timestamp creationDate;
    Date deadline;
    int projectId;
}
