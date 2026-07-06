package com.example.StudentRanker.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentGradeDto {
    private final Long id;
    private final String groupName;
    private final List<StudentGradeItemDto> grades;
    private final Double averageGrade;
}
