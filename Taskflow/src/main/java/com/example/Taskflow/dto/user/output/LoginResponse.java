package com.example.Taskflow.dto.user.output;

import lombok.Value;

@Value
public class LoginResponse {
    String token;
    String type="Bearer";
}
