package com.mert.taskmanagement.taskapp.entities;

import com.mert.taskmanagement.taskapp.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private  int id ;

    @Column(name = "task_title")
    private String title ;

    @Column(name = "task_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private Status status;

    @Column(name = "task_dueDate")
    private LocalDate dueDate ;

    @ManyToOne
    @JoinColumn(name = "assignedUser")  // foreign key olacak sütun adı
    private User assignedUser;

    @ManyToOne
    @JoinColumn(name = "project_id")  // foreign key olacak sütun adı
    private Project project;
}
