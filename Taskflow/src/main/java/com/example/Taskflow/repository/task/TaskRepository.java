package com.example.Taskflow.repository.task;

import com.example.Taskflow.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {

}
