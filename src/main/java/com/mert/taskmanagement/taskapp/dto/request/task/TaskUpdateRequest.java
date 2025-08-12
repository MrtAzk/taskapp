package com.mert.taskmanagement.taskapp.dto.request.task;

import com.mert.taskmanagement.taskapp.entities.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequest {

    private int id;
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
