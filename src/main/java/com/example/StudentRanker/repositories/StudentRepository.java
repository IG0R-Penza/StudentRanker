package com.example.StudentRanker.repositories;

import com.example.StudentRanker.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {
}
