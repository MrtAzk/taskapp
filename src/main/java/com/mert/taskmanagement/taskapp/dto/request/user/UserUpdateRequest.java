package com.mert.taskmanagement.taskapp.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    int id;
    @NotBlank
    @NotNull
    private String name;


    @NotBlank
    @NotNull
    private String password;
}
