package com.example.StudentRanker.repository;

import com.example.StudentRanker.dto.StudentRatingItemDto;
import com.example.StudentRanker.entity.GradeEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
    void deleteByStudentId(Long studentId);
    void deleteBySubjectId(Long subjectId);

    List<GradeEntity> getAllByStudentId(Long studentId);

    @Query(value = """
        select
            dense_rank() over (order by grade_sum desc, grade_count asc) as rating_position,
            student_id,
            stud.surname,
            stud.name,
            stud.patronymic,
            stud.group_name,
            grade_sum
        from (
            select
                student_id,
                sum(value) as grade_sum,
                count(*) as grade_count
            from grades_table
            group by student_id
        ) as sub
        join students_table stud on stud.id = sub.student_id
    """, nativeQuery = true)
    List<StudentRatingItemDto> getRatingByGradesSum();

    boolean existsByStudentIdAndSubjectIdAndSemester(Long studentId, Long subjectId, Short semester);

    GradeEntity getByStudentIdAndSubjectIdAndSemester(Long studentId, Long subjectId, Short semester);
}