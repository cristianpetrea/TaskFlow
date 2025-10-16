package com.example.Taskflow.dto.user.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "Numele nu poate fi gol!")
    private String name;
}
