package com.example.Taskflow.dto.project.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectDeleteRequest {

    @NotNull(message = "Id-ul proiectului este obligatoriu!")
    private int projectId;

}
