package com.example.StudentRanker.repository;

import com.example.StudentRanker.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
    void deleteByStudentId(Long studentId);
    void deleteBySubjectId(Long subjectId);
}
