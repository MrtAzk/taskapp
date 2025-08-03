package com.mert.taskmanagement.taskapp.dao;

import com.mert.taskmanagement.taskapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task,Integer> {
}
