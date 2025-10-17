package com.example.Taskflow.dto.project.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequest {

    @NotBlank
    String name;

    @NotBlank
    String description;

}
