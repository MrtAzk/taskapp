package com.mert.taskmanagement.taskapp.business.concretes;

import com.mert.taskmanagement.taskapp.business.abstracts.ITaskService;
import com.mert.taskmanagement.taskapp.core.exception.NotFoundException;
import com.mert.taskmanagement.taskapp.core.utils.Msg;
import com.mert.taskmanagement.taskapp.dao.TaskRepo;
import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskManager implements ITaskService {
    private final TaskRepo taskRepo;

    public TaskManager(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }
    @Override
    public Task save(Task task) {
        return this.taskRepo.save(task);
    }

    @Override
    public Task get(int id) {
        return this.taskRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Task update(Task task) {
        this.get(task.getId());
        return this.taskRepo.save(task);
    }

    @Override
    public Page<Task> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.taskRepo.findAll(pageable);
    }

    @Override
    public boolean delete(int id) {
       Task deletedTask=this.get(id);
        this.taskRepo.delete(deletedTask);
        return true;
    }
}
