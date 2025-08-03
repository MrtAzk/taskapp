package com.mert.taskmanagement.taskapp.dto.request.task;

import com.mert.taskmanagement.taskapp.entities.Project;
import com.mert.taskmanagement.taskapp.entities.User;
import com.mert.taskmanagement.taskapp.entities.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSaveRequest {

    @NotBlank
    private String title ;

    @NotBlank
    private String description;


    private Status status;


    @Positive
    private int assignedUserId;

    @Positive
    private int projectId;
}


