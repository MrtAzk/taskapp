package com.mert.taskmanagement.taskapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private  int id ;

    @Column(name = "task_name")
    private String name ;

    @Column(name = "task_description")
    private String description ;

    @Column(name = "task_createdDate")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")  // FK tanımı
    private User createdBy;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}
