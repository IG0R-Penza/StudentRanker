package com.example.StudentRanker.service;

import com.example.StudentRanker.dto.CreateGradeDto;
import com.example.StudentRanker.dto.EntityGradeDto;
import com.example.StudentRanker.dto.StudentGradeDto;
import com.example.StudentRanker.dto.StudentGradeItemDto;
import com.example.StudentRanker.entity.GradeEntity;
import com.example.StudentRanker.entity.StudentEntity;
import com.example.StudentRanker.entity.SubjectEntity;
import com.example.StudentRanker.mapper.GradeMapper;
import com.example.StudentRanker.repository.GradeRepository;
import com.example.StudentRanker.repository.StudentRepository;
import com.example.StudentRanker.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {
    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private GradeMapper gradeMapper;
    @InjectMocks
    private GradeService gradeService;

    @Test
    void createGrade_whenGradeNotExist(){
        CreateGradeDto dto = new CreateGradeDto(1L, 1L, (short) 1, (short) 100);

        when(gradeRepository.save(any(GradeEntity.class))).thenAnswer(invocation -> {
            var arg = invocation.getArgument(0, GradeEntity.class);
            var result = new GradeEntity();
            result.setId(1L);
            result.setStudentId(arg.getStudentId());
            result.setSubjectId(arg.getSubjectId());
            result.setValue(arg.getValue());
            return result;
        });
        when(gradeRepository.existsByStudentIdAndSubjectIdAndSemester(1L, 1L, (short) 1)).thenReturn(false);
        when(studentRepository.existsById(1L)).thenReturn(true);
        when(subjectRepository.existsById(1L)).thenReturn(true);
        when(gradeMapper.createDtoToEntity(any(CreateGradeDto.class))).thenAnswer(invocation -> {
            var arg = invocation.getArgument(0, CreateGradeDto.class);
            var result = new GradeEntity();
            result.setStudentId(arg.getStudentId());
            result.setSubjectId(arg.getSubjectId());
            result.setValue(arg.getValue());
            return result;
        });

        boolean res = gradeService.create(dto);
        assertTrue(res);
    }

    @Test
    void createGrade_whenGradeExist(){
        CreateGradeDto dto = new CreateGradeDto(1L, 1L, (short) 1, (short) 100);

        when(gradeRepository.existsByStudentIdAndSubjectIdAndSemester(1L, 1L, (short) 1)).thenReturn(true);

        boolean res = gradeService.create(dto);
        assertFalse(res);
    }

    @Test
    void createGrade_whenStudentNotExist(){
        CreateGradeDto dto = new CreateGradeDto(1L, 1L, (short) 1, (short) 100);

        when(gradeRepository.existsByStudentIdAndSubjectIdAndSemester(1L, 1L, (short) 1)).thenReturn(false);
        when(studentRepository.existsById(1L)).thenReturn(false);

        boolean res = gradeService.create(dto);
        assertFalse(res);
    }

    @Test
    void updateGrade_whenGradeExist(){
        EntityGradeDto dto = new EntityGradeDto(1L, 1L, 1L, (short) 1, (short) 100);

        when(gradeRepository.save(any(GradeEntity.class))).thenAnswer(invocation -> {
            var arg = invocation.getArgument(0, GradeEntity.class);
            var result = new GradeEntity();
            result.setId(arg.getId());
            result.setStudentId(arg.getStudentId());
            result.setSubjectId(arg.getSubjectId());
            result.setValue(arg.getValue());
            return result;
        });
        when(gradeRepository.existsById(1L)).thenReturn(true);
        when(studentRepository.existsById(1L)).thenReturn(true);
        when(subjectRepository.existsById(1L)).thenReturn(true);
        when(gradeMapper.entityDtoToEntity(any(EntityGradeDto.class))).thenAnswer(invocation -> {
            var arg = invocation.getArgument(0, EntityGradeDto.class);
            var result = new GradeEntity();
            result.setId(arg.getId());
            result.setStudentId(arg.getStudentId());
            result.setSubjectId(arg.getSubjectId());
            result.setValue(arg.getValue());
            return result;
        });

        boolean res = gradeService.update(dto);
        assertTrue(res);
    }

    @Test
    void updateGrade_whenGradeNotExist(){
        EntityGradeDto dto = new EntityGradeDto(1L, 1L, 1L, (short) 1, (short) 100);

        when(gradeRepository.existsById(1L)).thenReturn(false);

        boolean res = gradeService.update(dto);
        assertFalse(res);
    }

    @Test
    void updateGrade_whenGradeIsNotUnique(){
        EntityGradeDto dto = new EntityGradeDto(1L, 1L, 1L, (short) 1, (short) 100);

        when(gradeRepository.save(any(GradeEntity.class))).thenThrow(new DataIntegrityViolationException("Unique constraint failed"));
        when(gradeRepository.existsById(1L)).thenReturn(true);
        when(studentRepository.existsById(1L)).thenReturn(true);
        when(subjectRepository.existsById(1L)).thenReturn(true);
        when(gradeMapper.entityDtoToEntity(any(EntityGradeDto.class))).thenAnswer(invocation -> {
            var arg = invocation.getArgument(0, EntityGradeDto.class);
            var result = new GradeEntity();
            result.setId(arg.getId());
            result.setStudentId(arg.getStudentId());
            result.setSubjectId(arg.getSubjectId());
            result.setValue(arg.getValue());
            return result;
        });

        assertThrows(DataIntegrityViolationException.class, ()->gradeService.update(dto));
    }

    @Test
    void delete_existing() {
        Long id = 1L;
        when(gradeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(gradeRepository).deleteById(id);

        boolean res = gradeService.delete(id);

        assertTrue(res);
    }

    @Test
    void delete_nonExisting() {
        Long id = 1L;
        when(gradeRepository.existsById(1L)).thenReturn(false);

        boolean res = gradeService.delete(id);

        assertFalse(res);
    }

    @Test
    void getGradesByFullName_whenStudentsExist() {
        String surname = "Иванов";
        String name = "Иван";
        String patronymic = "Иванович";

        var subject1 = new SubjectEntity();
        subject1.setName("Математика");
        var subject2 = new SubjectEntity();
        subject2.setName("Физика");

        var student1 = new StudentEntity();
        student1.setId(1L);
        student1.setGroupName("24ВП1");

        var student2 = new StudentEntity();
        student2.setId(2L);
        student2.setGroupName("25ВП1");

        var grade1 = new GradeEntity();
        grade1.setSubject(subject1);
        grade1.setValue((short)80);
        grade1.setSemester((short) 1);
        var grade2 = new GradeEntity();
        grade2.setSubject(subject2);
        grade2.setValue((short)50);
        grade2.setSemester((short) 2);

        var grade3 = new GradeEntity();
        grade3.setSubject(subject1);
        grade3.setValue((short)100);
        grade3.setSemester((short) 1);
        var grade4 = new GradeEntity();
        grade4.setSubject(subject2);
        grade4.setValue((short)90);
        grade4.setSemester((short) 2);

        student1.setGrades(List.of(grade1, grade2));
        student2.setGrades(List.of(grade3, grade4));

        when(studentRepository.getAllBySurnameAndNameAndPatronymic(surname, name, patronymic)).thenReturn(List.of(student1, student2));

        var res = gradeService.getGradesByFullName(surname, name, patronymic);

        var ExpectedItem1 = new StudentGradeDto(1L, "24ВП1", List.of(new StudentGradeItemDto("Математика",
                (short) 1, (short) 80), new StudentGradeItemDto("Физика", (short)2, (short)50)), 65.0);
        var ExpectedItem2 = new StudentGradeDto(2L, "25ВП1", List.of(new StudentGradeItemDto("Математика",
                (short) 1, (short) 100), new StudentGradeItemDto("Физика", (short)2, (short)90)), 95.0);
        var expected = List.of(ExpectedItem1, ExpectedItem2);

        assertEquals(expected, res);
    }

    @Test
    void getGradesByFullName_whenStudentHasNoGrades() {
        String surname = "Иванов";
        String name = "Иван";
        String patronymic = "Иванович";

        var subject1 = new SubjectEntity();
        subject1.setName("Математика");
        var subject2 = new SubjectEntity();
        subject2.setName("Физика");

        var student1 = new StudentEntity();
        student1.setId(1L);
        student1.setGroupName("24ВП1");


        student1.setGrades(Collections.emptyList());

        when(studentRepository.getAllBySurnameAndNameAndPatronymic(surname, name, patronymic)).thenReturn(List.of(student1));

        var res = gradeService.getGradesByFullName(surname, name, patronymic);

        assertTrue(res.isEmpty());
    }

    @Test
    void getGradesByFullName_whenStudentNotExist() {
        when(studentRepository.getAllBySurnameAndNameAndPatronymic("Петров", "Пётр", "Петрович")).thenReturn(Collections.emptyList());

        var res = gradeService.getGradesByFullName("Петров", "Пётр", "Петрович");

        assertTrue(res.isEmpty());
    }
}
