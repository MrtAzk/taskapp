package com.mert.taskmanagement.taskapp.business.abstracts;

import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.User;
import org.springframework.data.domain.Page;

public interface IProjectService {
    Project save(Project project);

    Project get(int id);

    Project update(Project project);

    Page<Project> findAll(int page, int pageSize);

    boolean delete(int id);
}
