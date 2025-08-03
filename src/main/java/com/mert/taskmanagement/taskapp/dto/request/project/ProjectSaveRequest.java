package com.mert.taskmanagement.taskapp.dto.request.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSaveRequest {
    @NotBlank
    private String name ;

    @NotBlank
    private String description ;

    private List<Integer> tasks;
}

