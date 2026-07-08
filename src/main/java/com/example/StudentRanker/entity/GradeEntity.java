package com.example.StudentRanker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "grades_table",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject_id", "semester"})
)
public class GradeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Long studentId;

    @Column(name = "subject_id", insertable = false, updatable = false)
    private Long subjectId;

    @Column(name = "semester")
    private Short semester;

    @Column(name = "value")
    private Short value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subject;
}
