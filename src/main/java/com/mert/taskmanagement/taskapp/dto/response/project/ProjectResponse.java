package com.mert.taskmanagement.taskapp.dto.response.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mert.taskmanagement.taskapp.entities.Task;
import com.mert.taskmanagement.taskapp.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private  int id ;

    private String name ;

    private String description ;

    private int createdById;

    private List<Integer> tasksId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;
}
