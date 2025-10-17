package com.example.Taskflow.controller.project;

import com.example.Taskflow.dto.project.input.ProjectRequest;
import com.example.Taskflow.dto.project.output.ProjectResponse;
import com.example.Taskflow.service.project.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;


    @PostMapping("/createProject")
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request, Authentication authentication) {

        String userEmail = authentication.getName();
        ProjectResponse projectResponse = projectService.createProject(userEmail, request);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectResponse.getId())
                .toUri();


        return ResponseEntity
                .created(location)
                .body(projectResponse);
    }

}
