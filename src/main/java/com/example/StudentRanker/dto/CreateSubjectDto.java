package com.example.StudentRanker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSubjectDto {
    @NotBlank
    private final String name;
}
