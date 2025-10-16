package com.example.Taskflow.controller.user;


import com.example.Taskflow.dto.user.UserRegisterRequest;
import com.example.Taskflow.dto.user.UserResponse;
import com.example.Taskflow.models.user.User;
import com.example.Taskflow.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest request){
        UserResponse response=userService.registerUser(request);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
