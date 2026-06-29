package com.example.StudentRanker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStudentDto {
    @NotBlank
    private final String surname;
    @NotBlank
    private final String name;
    private final String patronymic;
    @NotBlank
    private final String groupName;
}
