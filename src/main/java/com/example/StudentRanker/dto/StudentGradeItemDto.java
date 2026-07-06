package com.example.StudentRanker.dto;

import lombok.Data;

@Data
public class StudentGradeItemDto {
    private final String subjectName;
    private final Short semester;
    private final Short value;
}
