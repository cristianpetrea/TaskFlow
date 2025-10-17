package com.example.Taskflow.repository.project;

import com.example.Taskflow.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Optional<Project> findById(int userId);

    boolean existsByName(String name);
}
