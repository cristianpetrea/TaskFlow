package com.example.Taskflow.dto.task.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class TaskUpdateRequest {

    @NotNull
    private int taskId;


    private String title;


    private String description;


    private String priority;

    @NotNull
    private Date deadline;


    private String status;

    @NotNull(message = "Id-ul proiectului este obligatoriu!")
    private int projectId;
}
