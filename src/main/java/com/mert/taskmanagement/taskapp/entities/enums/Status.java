package com.mert.taskmanagement.taskapp.entities.enums;

import lombok.Getter;

@Getter
public enum Status {
    TODO("Yapılacak"),
    IN_PROGRESS("Devam Ediyor"),
    DONE("Tamamlandı");

    private final String label;

    Status(String label) {
        this.label = label;
    }


}