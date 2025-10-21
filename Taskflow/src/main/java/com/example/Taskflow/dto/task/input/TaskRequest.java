package com.example.Taskflow.dto.task.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class TaskRequest {
    @NotBlank(message = "Titlul nu poate fi gol")
    private String title;

    @NotBlank(message = "Descrierea nu poate fi goala")
    private String description;

    @NotBlank(message = "Trebuie selectata un grad de prioritate!")
    private String priority;

    @NotNull(message = "Trebuie selectata o data limita!")
    private Date deadline;

    @NotNull(message = "Id-ul proiectului este obligatoriu!")
    private int projectId;

}
