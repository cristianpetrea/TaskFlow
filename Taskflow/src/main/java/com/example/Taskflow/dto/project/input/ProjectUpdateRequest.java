package com.example.Taskflow.dto.project.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectUpdateRequest {
    @NotNull(message = "Id-ul proiectului este obligatoriu!")
    private int projectId;

    @NotBlank(message = "Numele proiectului nu poate fi gol!")
    private String name;

    @NotBlank(message = "Descrierea nu poate fi goala!")
    private String description;
}
