package com.example.StudentRanker.repository;

import com.example.StudentRanker.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> getAllBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    @Query("select student.id from StudentEntity student")
    List<Long> getAllIds();
}
