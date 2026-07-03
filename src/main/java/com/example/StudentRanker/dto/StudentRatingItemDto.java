package com.example.StudentRanker.dto;

import lombok.Data;

@Data
public class StudentRatingItemDto {
    private final Long ratingPosition;
    private final Long studentId;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final String groupName;
    private final Long gradeSum;
}
