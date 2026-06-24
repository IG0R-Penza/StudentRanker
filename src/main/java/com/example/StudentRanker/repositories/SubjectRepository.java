package com.example.StudentRanker.repositories;

import com.example.StudentRanker.models.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectModel, Long> {
}
