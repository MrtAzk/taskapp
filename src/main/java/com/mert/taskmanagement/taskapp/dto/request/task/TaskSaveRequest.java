package com.mert.taskmanagement.taskapp.dto.request.task;


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




    @Positive
    private int assignedUserId;

    @Positive
    private int projectId;
}


