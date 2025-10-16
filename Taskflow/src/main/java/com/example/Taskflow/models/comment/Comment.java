package com.example.Taskflow.models.comment;

import com.example.Taskflow.models.task.Task;
import com.example.Taskflow.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name="comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Timestamp adding_date;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="task_id",nullable = false)
    private Task task;
}
