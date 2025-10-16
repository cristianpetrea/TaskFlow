package com.example.Taskflow.model.task;

import com.example.Taskflow.model.comment.Comment;
import com.example.Taskflow.model.log.ActivityLog;
import com.example.Taskflow.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private Timestamp creation_date;

    @Column(nullable = false)
    private Date deadline;

    @ManyToMany(mappedBy = "userTask",fetch = FetchType.LAZY)
    private Set<User> user=new HashSet<>();

    @OneToMany(mappedBy = "task",fetch =FetchType.LAZY)
    private Set<ActivityLog> taskLog=new HashSet<>();

    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY)
    private Set<Comment> taskComment=new HashSet<>();
}
