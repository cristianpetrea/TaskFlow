package com.example.Taskflow.controller.task;

import com.example.Taskflow.dto.task.input.TaskRequest;
import com.example.Taskflow.dto.task.input.TaskUpdateRequest;
import com.example.Taskflow.dto.task.output.TaskResponse;
import com.example.Taskflow.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class Taskcontroller {

    private final TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@Valid@RequestBody TaskRequest request, Authentication authentication){

        String email=authentication.getName();
        TaskResponse taskResponse=taskService.createTask(email,request);

        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }
    @PostMapping("/updateTask")
    public ResponseEntity<TaskResponse> updateTask(@Valid@RequestBody TaskUpdateRequest request,Authentication authentication){
        String email=authentication.getName();
        TaskResponse taskResponse=taskService.updateTask(email,request);

        return ResponseEntity.ok(taskResponse);
    }
}
