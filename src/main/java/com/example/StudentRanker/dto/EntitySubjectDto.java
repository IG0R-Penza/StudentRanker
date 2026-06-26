package com.example.StudentRanker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntitySubjectDto {
    private final Long id;
    @NotBlank
    private final String name;
}
