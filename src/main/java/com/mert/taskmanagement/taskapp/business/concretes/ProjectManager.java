package com.mert.taskmanagement.taskapp.business.concretes;

import com.mert.taskmanagement.taskapp.business.abstracts.IProjectService;
import com.mert.taskmanagement.taskapp.core.exception.NotFoundException;
import com.mert.taskmanagement.taskapp.core.utils.Msg;
import com.mert.taskmanagement.taskapp.dao.ProjectRepo;
import com.mert.taskmanagement.taskapp.dao.UserRepo;
import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProjectManager implements IProjectService {
    private final ProjectRepo projectRepo;


    public ProjectManager(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }


    @Override
    public Project save(Project project) {

        return this.projectRepo.save(project);
    }

    @Override
    public Project get(int id) {
        return this.projectRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Project update(Project project) {
        this.get(project.getId());
        return this.projectRepo.save(project);
    }

    @Override
    public Page<Project> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.projectRepo.findAll(pageable);
    }

    @Override
    public boolean delete(int id) {
        Project deletedProject=this.get(id);
        this.projectRepo.delete(deletedProject);
        return true;
    }
}
