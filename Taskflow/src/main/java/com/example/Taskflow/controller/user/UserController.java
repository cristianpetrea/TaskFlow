package com.example.Taskflow.controller.user;


import com.example.Taskflow.dto.user.input.UserRegisterRequest;
import com.example.Taskflow.dto.user.input.UserUpdateRequest;
import com.example.Taskflow.dto.user.output.UserResponse;
import com.example.Taskflow.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/me")
    public ResponseEntity<UserResponse> getMe(Authentication authentication){
        String userEmail = authentication.getName();
        UserResponse response=userService.getUserByEmailAndMap(userEmail);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateProfile(@Valid @RequestBody UserUpdateRequest request, Authentication authentication){
        String userEmail=authentication.getName();

        UserResponse response=userService.updateProfile(userEmail,request);

        return ResponseEntity.ok(response);
    }
}
