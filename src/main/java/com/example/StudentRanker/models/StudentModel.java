package com.example.StudentRanker.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "students_table")
public class StudentModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "group_name", nullable = false)
    private String group_name;
}
