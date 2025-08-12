package com.mert.taskmanagement.taskapp.api;

import com.mert.taskmanagement.taskapp.business.abstracts.IProjectService;
import com.mert.taskmanagement.taskapp.business.abstracts.ITaskService;
import com.mert.taskmanagement.taskapp.business.abstracts.IUserService;
import com.mert.taskmanagement.taskapp.core.config.modelMapper.IModelMapperService;
import com.mert.taskmanagement.taskapp.core.result.ResultData;
import com.mert.taskmanagement.taskapp.core.utils.CreateResult;
import com.mert.taskmanagement.taskapp.dto.request.task.TaskSaveRequest;
import com.mert.taskmanagement.taskapp.dto.request.task.TaskUpdateRequest;
import com.mert.taskmanagement.taskapp.dto.response.CursorResponse;
import com.mert.taskmanagement.taskapp.dto.response.task.TaskResponse;
import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.Task;
import com.mert.taskmanagement.taskapp.entities.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final IModelMapperService modelMapper;
    private final ITaskService taskService;
    private final IUserService userService;
    private final IProjectService projectService;

    public TaskController(IModelMapperService modelMapper, ITaskService taskService, IUserService userService, IProjectService projectService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse save(@Valid @RequestBody TaskSaveRequest taskSaveRequest){
        Task saveTask =this.modelMapper.forRequest().map(taskSaveRequest, Task.class);



        // User ve Project entity'lerini set et
        User assignedUser = userService.get(taskSaveRequest.getAssignedUserId());
        Project project = projectService.get(taskSaveRequest.getProjectId());

        saveTask.setAssignedUser(assignedUser);
        saveTask.setProject(project);
        saveTask.setDueDate(LocalDate.now().plusDays(7)); // Default 7 gün sonra

        this.taskService.save(saveTask);


        TaskResponse taskResponse =this.modelMapper.forResponse().map(saveTask,TaskResponse.class);
        return taskResponse;

    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse update (@Valid @RequestBody TaskUpdateRequest taskUpdateRequest){
       Task updatedTask=this.modelMapper.forRequest().map(taskUpdateRequest,Task.class);

        User assignedUser = userService.get(taskUpdateRequest.getAssignedUserId());
        Project project = projectService.get(taskUpdateRequest.getProjectId());

        updatedTask.setAssignedUser(assignedUser);
        updatedTask.setProject(project);
        this.taskService.save(updatedTask);

        TaskResponse taskResponse=this.modelMapper.forResponse().map(updatedTask, TaskResponse.class);
        return taskResponse;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<TaskResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                              @RequestParam(name = "pagesize",required = false,defaultValue = "10")int pageSize)
    {

        Page<Task> tasks =this.taskService.findAll(page,pageSize);

        Page<TaskResponse> taskResponsePage =tasks.map(task -> this.modelMapper.forResponse().map(task, TaskResponse.class));

        return CreateResult.getAll(taskResponsePage);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<TaskResponse> get(@PathVariable("id") int id){
        Task task=this.taskService.get(id);
        TaskResponse taskResponse=this.modelMapper.forResponse().map(task,TaskResponse.class);
        return CreateResult.success(taskResponse);
    }
}
