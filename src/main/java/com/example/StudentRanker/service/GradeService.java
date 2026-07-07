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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger log = LogManager.getLogger(GradeService.class);

    public boolean create(CreateGradeDto createGradeDto) {
        if (!gradeRepository.existsByStudentIdAndSubjectIdAndSemester(createGradeDto.getStudentId(), createGradeDto.getSubjectId(), createGradeDto.getSemester()) && studentRepository.existsById(createGradeDto.getStudentId()) && subjectRepository.existsById(createGradeDto.getSubjectId())){
            GradeEntity gradeEntity = gradeMapper.createDtoToEntity(createGradeDto);
            gradeEntity.setStudent(studentRepository.getReferenceById(createGradeDto.getStudentId()));
            gradeEntity.setSubject(subjectRepository.getReferenceById(createGradeDto.getSubjectId()));
            var saved = gradeRepository.save(gradeEntity);
            log.info("Оценка успешно создана. gradeId={}, studentId={}, subjectId={}, semester={}, value={}",
                    saved.getId(), saved.getStudentId(), saved.getSubjectId(), saved.getSemester(), saved.getValue());
            return true;
        }
        else {
            log.info("Оценка уже существует или не существуют студент или предмет. studentId={}, subjectId={}, semester={}",
                    createGradeDto.getStudentId(), createGradeDto.getSubjectId(), createGradeDto.getSemester());
            return false;
        }
    }

    public boolean update(EntityGradeDto entityGradeDto) {
        if (gradeRepository.existsById(entityGradeDto.getId()) && studentRepository.existsById(entityGradeDto.getStudentId()) && subjectRepository.existsById(entityGradeDto.getSubjectId())) {
            GradeEntity gradeEntity = gradeMapper.entityDtoToEntity(entityGradeDto);
            gradeEntity.setStudent(studentRepository.getReferenceById(entityGradeDto.getStudentId()));
            gradeEntity.setSubject(subjectRepository.getReferenceById(entityGradeDto.getSubjectId()));
            var saved = gradeRepository.save(gradeEntity);
            log.info("Оценка успешно обновлена. gradeId={}, studentId={}, subjectId={}, semester={}, value={}",
                    saved.getId(), saved.getStudentId(), saved.getSubjectId(), saved.getSemester(), saved.getValue());
            return true;
        }
        else {
            log.info("Оценка не существует или не существует предмет или студент. studentId={}, subjectId={}, semester={}",
                    entityGradeDto.getStudentId(), entityGradeDto.getSubjectId(), entityGradeDto.getSemester());
            return false;
        }
    }

    public boolean delete(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            log.info("Оценка удалена. id={}", id);
            return true;
        }
        else {
            log.info("Оценка не существует. id={}", id);
            return false;
        }
    }

    public List<StudentGradeDto> getGradesByFullName(String surname, String name, String patronymic){
        List<StudentGradeDto> res = new ArrayList<>();

        List<StudentEntity> students = studentRepository.getAllBySurnameAndNameAndPatronymic(surname, name, patronymic);

        for (var student : students) {
            List<StudentGradeItemDto> gradeItemDtos = new ArrayList<>();
            int gradeSum = 0;

            //List<GradeEntity> gradeEntities = gradeRepository.getAllByStudentId(student.getId());
            //for (var grade : gradeEntities) {
            for (var grade : student.getGrades()) {
                gradeSum+=grade.getValue();
                gradeItemDtos.add(new StudentGradeItemDto(grade.getSubject().getName(), grade.getSemester(), grade.getValue()));
                //gradeItemDtos.add(new StudentGradeItemDto(subjectRepository.getReferenceById(grade.getSubjectId()).getName(), grade.getSemester(), grade.getValue()));
            }

            if (!gradeItemDtos.isEmpty()) {
                res.add(new StudentGradeDto(student.getId(), student.getGroupName(), gradeItemDtos, gradeSum/(double)gradeItemDtos.size()));
            }
        }

        log.info("Найдено {} студентов c оценками по ФИО", res.size());
        return res;
    }

    public List<StudentRatingItemDto> getStudentRating() {
        var res = gradeRepository.getRatingByGradesSum();
        log.info("Составлен рейтинг из {} студентов", res.size());
        return res;
    }

}
