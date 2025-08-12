package com.mert.taskmanagement.taskapp.entities;

import com.mert.taskmanagement.taskapp.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name",nullable = false)
    private String name;

    @Column(name = "user_email",unique = true)
    private String email ;

    @Column(name = "user_password",nullable = false)
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role ;

    @Column(name = "user_created_time",nullable = false)
    @CreationTimestamp
    private LocalDate createdAt ;

    @OneToMany(mappedBy = "assignedUser")
    private List<Task> tasks;


}
