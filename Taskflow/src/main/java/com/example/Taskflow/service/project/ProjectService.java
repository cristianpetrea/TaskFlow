package com.example.Taskflow.service.project;

import com.example.Taskflow.dto.project.input.ProjectRequest;
import com.example.Taskflow.dto.project.output.ProjectResponse;
import com.example.Taskflow.exception.ProjectAlreadyExistsException;
import com.example.Taskflow.exception.UserNotFoundException;
import com.example.Taskflow.model.project.Project;
import com.example.Taskflow.model.user.User;
import com.example.Taskflow.repository.project.ProjectRepository;
import com.example.Taskflow.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponse createProject(String email, ProjectRequest request){
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("Nu e conectat niciun utizator"));


        if(projectRepository.existsByName(request.getName())){
            throw new ProjectAlreadyExistsException("Deja exista un proiect cu acest nume!");
        }

        Project newProject=new Project();

        newProject.setName(request.getName());
        newProject.setDescription(request.getDescription());
        newProject.setCreation_date(new Timestamp(System.currentTimeMillis()));
        newProject.setUser(user);

        Project projectSaved=projectRepository.save(newProject);

        return new ProjectResponse(
                projectSaved.getId(),
                projectSaved.getName(),
                projectSaved.getDescription(),
                projectSaved.getCreation_date(),
                projectSaved.getUser().getId()
        );

    }
}
