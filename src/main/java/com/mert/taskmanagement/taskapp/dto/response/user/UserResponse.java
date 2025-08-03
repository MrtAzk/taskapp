package com.mert.taskmanagement.taskapp.dto.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mert.taskmanagement.taskapp.entities.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private int id;


    private String name;


    private String email ;


    private String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt ;


    private List<Integer> taskIds;

}
