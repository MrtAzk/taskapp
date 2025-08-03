package com.mert.taskmanagement.taskapp.dao;

import com.mert.taskmanagement.taskapp.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Integer> {
}
