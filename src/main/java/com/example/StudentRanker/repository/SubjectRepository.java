package com.example.StudentRanker.repository;

import com.example.StudentRanker.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    @Query("select subject.id from SubjectEntity subject")
    List<Long> getAllIds();
}
