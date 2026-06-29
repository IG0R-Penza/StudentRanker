package com.example.StudentRanker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntityStudentDto {
    private final Long id;
    @NotBlank
    private final String surname;
    @NotBlank
    private final String name;
    private final String patronymic;
    @NotBlank
    private final String groupName;
}
