package com.mert.taskmanagement.taskapp.dto.response.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String username;
    private String email;

    public AuthResponse(String message, String username, String email) {
        this.message = message;
        this.username = username;
        this.email = email;
    }

    public AuthResponse(String message) {
        this.message = message;
    }
}
