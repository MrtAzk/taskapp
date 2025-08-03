package com.mert.taskmanagement.taskapp.business.abstracts;

import com.mert.taskmanagement.taskapp.entities.Task;
import com.mert.taskmanagement.taskapp.entities.User;
import org.springframework.data.domain.Page;

public interface ITaskService {
    Task  save(Task task);

    Task get(int id);

    Task update(Task task);

    Page<Task> findAll(int page, int pageSize);

    boolean delete(int id);
}
