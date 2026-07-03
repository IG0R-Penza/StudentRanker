package com.example.StudentRanker.service;

import com.example.StudentRanker.dto.*;
import com.example.StudentRanker.entity.GradeEntity;
import com.example.StudentRanker.entity.StudentEntity;
import com.example.StudentRanker.mapper.GradeMapper;
import com.example.StudentRanker.repository.GradeRepository;
import com.example.StudentRanker.repository.StudentRepository;
import com.example.StudentRanker.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    public List<StudentGradeDto> getGradesByFullName(String surname, String name, String patronymic){
        List<StudentGradeDto> res = new ArrayList<>();

        List<StudentEntity> students = studentRepository.getAllBySurnameAndNameAndPatronymic(surname, name, patronymic);

        for (var student : students) {
            List<StudentGradeItemDto> gradeItemDtos = new ArrayList<>();
            int gradeSum = 0;

            List<GradeEntity> gradeEntities = gradeRepository.getAllByStudentId(student.getId());
            for (var grade : gradeEntities) {
                gradeSum+=grade.getValue();
                gradeItemDtos.add(new StudentGradeItemDto(subjectRepository.getReferenceById(grade.getSubjectId()).getName(), grade.getSemester(), grade.getValue()));
            }

            if (!gradeItemDtos.isEmpty()) {
                res.add(new StudentGradeDto(student.getId(), student.getGroupName(), gradeItemDtos, gradeSum/(double)gradeItemDtos.size()));
            }
        }

        return res;
    }

    public List<StudentRatingItemDto> getStudentRating() {
        return gradeRepository.getRatingByGradesSum();
    }

}
