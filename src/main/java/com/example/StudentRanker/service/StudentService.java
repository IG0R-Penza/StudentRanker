package com.example.StudentRanker.service;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.mapper.StudentMapper;
import com.example.StudentRanker.repository.GradeRepository;
import com.example.StudentRanker.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private final GradeRepository gradeRepository;

    public void create(CreateStudentDto studentDto) {
        studentRepository.save(studentMapper.createDtoToModel(studentDto));
    }

    public List<EntityStudentDto> readAll() { return studentMapper.entitiesToEntityDto(studentRepository.findAll()); }

    public EntityStudentDto read(Long id) {
        if (studentRepository.existsById(id)) return studentMapper.entityToEntityDto(studentRepository.getReferenceById(id));
        else return null;
    }

    public boolean update(EntityStudentDto entityStudentDto){
        if (studentRepository.existsById(entityStudentDto.getId())) {
            studentRepository.save(studentMapper.entityDtoToModel(entityStudentDto));
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (studentRepository.existsById(id)) {
            gradeRepository.deleteByStudentId(id);
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}