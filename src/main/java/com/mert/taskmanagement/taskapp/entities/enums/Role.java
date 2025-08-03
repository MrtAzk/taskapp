package com.mert.taskmanagement.taskapp.entities.enums;

public enum Role {
    ROLE_ADMIN("ADMİN"),
    ROLE_USER("KULLANICI");

    private  String  role ;

    Role(String role) {
        this.role = role;
    }
}
