package com.example.StudentRanker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "students_table")
public class StudentEntity {
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
    private String groupName;

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GradeEntity> grades = new ArrayList<>();
}
