package com.mert.taskmanagement.taskapp.entities;

import com.mert.taskmanagement.taskapp.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name",nullable = false)
    private String name;

    @Column(name = "user_email",unique = true,nullable = false)
    private String email ;

    @Column(name = "user_password",nullable = false)
    private String password;

    @Column(name = "user-role")
    private Role role = Role.ROLE_USER;

    @Column(name = "user_created_time",nullable = false)
    private LocalDate createdAt ;

    @OneToMany(mappedBy = "assignedUser")
    private List<Task> tasks;


}
