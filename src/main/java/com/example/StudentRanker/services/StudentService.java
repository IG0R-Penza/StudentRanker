package com.example.StudentRanker.services;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.mappers.StudentMapper;
import com.example.StudentRanker.models.StudentModel;
import com.example.StudentRanker.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public void create(CreateStudentDto studentDto) {
        studentRepository.save(studentMapper.createDtoToModel(studentDto));
    }

    public List<EntityStudentDto> readAll() { return studentMapper.modelsToEntityDto(studentRepository.findAll()); }

    public EntityStudentDto read(Long id) {
        if (studentRepository.existsById(id)) return studentMapper.modelToEntityDto(studentRepository.getOne(id));
        else return null;
    }

    public boolean update(EntityStudentDto entityStudentDto){
        if (studentRepository.existsById(entityStudentDto.getId())) {
            StudentModel studentModel = studentMapper.entityDtoToModel(entityStudentDto);
            studentRepository.save(studentModel);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}