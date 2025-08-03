package com.mert.taskmanagement.taskapp.core.config.modelMapper;

import com.mert.taskmanagement.taskapp.dto.request.project.ProjectSaveRequest;
import com.mert.taskmanagement.taskapp.dto.request.task.TaskSaveRequest;
import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.Task;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperConfig implements  IModelMapperService {
    private final ModelMapper modelMapper;

    @Autowired
    public ModelMapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);

        // TaskSaveRequest'ten Task'e dönüşüm yaparken ID'yi atla
        this.modelMapper.typeMap(TaskSaveRequest.class, Task.class).addMappings(mapper -> mapper.skip(Task::setId));

        // TaskSaveRequest'ten Task'e dönüşüm yaparken ID'yi atla
        this.modelMapper.typeMap(ProjectSaveRequest.class, Project.class).addMappings(mapper -> mapper.skip(Project::setId));

        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
