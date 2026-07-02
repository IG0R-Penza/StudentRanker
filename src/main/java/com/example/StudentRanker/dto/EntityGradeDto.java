package com.example.StudentRanker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntityGradeDto {
    @NotNull
    private final Long id;
    @NotNull
    private final Long studentId;
    @NotNull
    private final Long subjectId;
    @NotNull
    private final Short semester;
    @Min(0)
    @Max(100)
    private final Short value;
}