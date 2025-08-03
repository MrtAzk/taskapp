package com.mert.taskmanagement.taskapp.api;

import com.mert.taskmanagement.taskapp.business.abstracts.IProjectService;
import com.mert.taskmanagement.taskapp.business.abstracts.IUserService;
import com.mert.taskmanagement.taskapp.core.config.modelMapper.IModelMapperService;
import com.mert.taskmanagement.taskapp.core.result.ResultData;
import com.mert.taskmanagement.taskapp.core.utils.CreateResult;
import com.mert.taskmanagement.taskapp.dto.request.project.ProjectSaveRequest;
import com.mert.taskmanagement.taskapp.dto.request.project.ProjectUpdateRequest;
import com.mert.taskmanagement.taskapp.dto.request.user.UserUpdateRequest;
import com.mert.taskmanagement.taskapp.dto.response.CursorResponse;
import com.mert.taskmanagement.taskapp.dto.response.project.ProjectResponse;
import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.Task;
import com.mert.taskmanagement.taskapp.entities.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/projects")
public class ProjectController {
    private final IProjectService projectService ;
    private final IModelMapperService modelMapper;
    private final IUserService userService;


    public ProjectController(IProjectService projectService, IModelMapperService modelMapper, IUserService userService) {
        this.projectService = projectService;
        this.modelMapper = modelMapper;
        this.userService = userService;

    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse save(@Valid @RequestBody ProjectSaveRequest projectSaveRequest){
        Project saveProject =this.modelMapper.forRequest().map(projectSaveRequest, Project.class);
        saveProject.setCreatedAt(LocalDate.now());



        this.projectService.save(saveProject);


        ProjectResponse projectResponse =this.modelMapper.forResponse().map(saveProject,ProjectResponse.class);
        return projectResponse;

    }
    @PutMapping("/{id}")
    public ProjectResponse update (@Valid @RequestBody ProjectUpdateRequest projectUpdateRequest){
        Project updatedProject=this.modelMapper.forRequest().map(projectUpdateRequest,Project.class);
        this.projectService.save(updatedProject);

        ProjectResponse projectResponse=this.modelMapper.forResponse().map(updatedProject, ProjectResponse.class);
        return projectResponse;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<ProjectResponse>> getAll(@RequestParam(name = "page",required = false,defaultValue = "0") int page,
                                                           @RequestParam(name = "pagesize",required = false,defaultValue = "10")int pageSize)
    {

        Page<Project> projects = this.projectService.findAll(page, pageSize);

        // Manuel mapping - ModelMapper'ı bypass et
        Page<ProjectResponse> projectResponsePage = projects.map(project -> {
            ProjectResponse response = new ProjectResponse();
            response.setId(project.getId());
            response.setName(project.getName());
            response.setDescription(project.getDescription());
            response.setCreatedAt(project.getCreatedAt());



                List<Integer> taskIds = project.getTasks().stream()
                        .map(Task::getId)
                        .collect(Collectors.toList());
                response.setTasksId(taskIds);


            return response;
        });

        return CreateResult.getAll(projectResponsePage);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ProjectResponse> get(@PathVariable("id") int id){
        Project project=this.projectService.get(id);
        ProjectResponse projectResponse=this.modelMapper.forResponse().map(project,ProjectResponse.class);
        return CreateResult.success(projectResponse);
    }

}
