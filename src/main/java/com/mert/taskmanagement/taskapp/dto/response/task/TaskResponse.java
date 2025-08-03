package com.mert.taskmanagement.taskapp.dto.response.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mert.taskmanagement.taskapp.entities.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private int id;

    private String title ;

    private String description;

    private Status status;

    private int assignedUserId;

    private int projectId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate ;
}
