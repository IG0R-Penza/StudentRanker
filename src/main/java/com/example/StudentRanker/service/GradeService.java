package com.example.StudentRanker.service;

import com.example.StudentRanker.dto.CreateGradeDto;
import com.example.StudentRanker.dto.EntityGradeDto;
import com.example.StudentRanker.mapper.GradeMapper;
import com.example.StudentRanker.repository.GradeRepository;
import com.example.StudentRanker.repository.StudentRepository;
import com.example.StudentRanker.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public boolean create(CreateGradeDto createGradeDto) {
        if (studentRepository.existsById(createGradeDto.getStudentId()) && subjectRepository.existsById(createGradeDto.getSubjectId())){
            gradeRepository.save(gradeMapper.createDtoToEntity(createGradeDto));
            return true;
        }
        else return false;
    }

    public boolean update(EntityGradeDto entityGradeDto) {
        if (gradeRepository.existsById(entityGradeDto.getId()) && studentRepository.existsById(entityGradeDto.getStudentId()) && subjectRepository.existsById(entityGradeDto.getSubjectId())) {
            gradeRepository.save(gradeMapper.entityDtoToEntity(entityGradeDto));
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
