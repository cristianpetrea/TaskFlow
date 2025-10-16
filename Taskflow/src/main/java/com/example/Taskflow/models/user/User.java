package com.example.Taskflow.models.user;


import com.example.Taskflow.models.comment.Comment;
import com.example.Taskflow.models.log.ActivityLog;
import com.example.Taskflow.models.project.Project;
import com.example.Taskflow.models.task.Task;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Timestamp registerDate;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> ownedProject=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="task_assignees",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="task_id")
    )
    private Set<Task> userTask = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch =FetchType.LAZY)
    private Set<ActivityLog> userLogs=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Comment> userComment=new HashSet<>();

}
