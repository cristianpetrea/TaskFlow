package com.example.Taskflow.service.task;


import com.example.Taskflow.dto.task.input.TaskRequest;
import com.example.Taskflow.dto.task.input.TaskUpdateRequest;
import com.example.Taskflow.dto.task.output.TaskResponse;
import com.example.Taskflow.exception.InvalidTaskException;
import com.example.Taskflow.exception.NoAccesException;
import com.example.Taskflow.exception.ProjectNotFoundException;
import com.example.Taskflow.exception.UserNotFoundException;
import com.example.Taskflow.model.project.Project;
import com.example.Taskflow.model.user.User;
import com.example.Taskflow.model.task.Task;
import com.example.Taskflow.repository.project.ProjectRepository;
import com.example.Taskflow.repository.task.TaskRepository;
import com.example.Taskflow.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    public TaskResponse createTask(String userEmail, TaskRequest request){

        User user= userRepository.findByEmail(userEmail)
                .orElseThrow(()->new UserNotFoundException("Nu e conectat niciun utizator"));

        Project project=projectRepository.findById(request.getProjectId())
                .orElseThrow(()-> new ProjectNotFoundException("Nu s-a gasit un proiect cu acest id!"));

        if(project.getUser().getId()!=user.getId())
        {
            throw new NoAccesException("Nu ai acces pe acest proiect!");
        }
        Task newTask = new Task();

        newTask.setTitle(request.getTitle());
        newTask.setDescription(request.getDescription());
        newTask.setPriority(request.getPriority());
        newTask.setDeadline(request.getDeadline());
        newTask.setProject(project);
        newTask.setCreationDate(new Timestamp(System.currentTimeMillis()));

        Task createTask=taskRepository.save(newTask);

        return new TaskResponse(
                createTask.getId(),
                createTask.getTitle(),
                createTask.getDescription(),
                createTask.getStatus(),
                createTask.getPriority(),
                createTask.getCreationDate(),
                createTask.getDeadline(),
                createTask.getProject().getId()
        );
    }

    public TaskResponse updateTask(String userEmail, TaskUpdateRequest request) {
        User authenticatedUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("Nu e niciun user conectat!"));

        Task task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new InvalidTaskException("Nu exista un task valid!"));

        Project project = task.getProject();

        if (project.getUser().getId()!=authenticatedUser.getId()) {
            throw new NoAccesException("Nu ai permisiunea de a modifica acest task!");
        }

        if(request.getTitle()!=null && !request.getTitle().trim().isEmpty()){
            task.setTitle(request.getTitle());
        }

        if(request.getDescription()!=null && !request.getDescription().trim().isEmpty()){
            task.setDescription(request.getDescription());
        }
        if(request.getPriority()!=null && !request.getPriority().trim().isEmpty()){
            task.setPriority(request.getPriority());
        }
        if(request.getDeadline()!=null){
            task.setDeadline(request.getDeadline());
        }
        if(request.getStatus()!=null && !request.getStatus().trim().isEmpty()){
            task.setStatus(request.getStatus());
        }

        Task updatedTask = taskRepository.save(task);

        return new TaskResponse(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getStatus(),
                updatedTask.getPriority(),
                updatedTask.getCreationDate(),
                updatedTask.getDeadline(),
                updatedTask.getProject().getId()
        );
    }
}
