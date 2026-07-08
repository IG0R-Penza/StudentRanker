package com.example.StudentRanker.scheduler;

import com.example.StudentRanker.dto.CreateGradeDto;
import com.example.StudentRanker.dto.EntityGradeDto;
import com.example.StudentRanker.entity.GradeEntity;
import com.example.StudentRanker.repository.GradeRepository;
import com.example.StudentRanker.repository.StudentRepository;
import com.example.StudentRanker.repository.SubjectRepository;
import com.example.StudentRanker.service.GradeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class RandomGradeScheduler {
    private final GradeService gradesService;
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final Random random = new Random();
    private static final Logger log = LogManager.getLogger(RandomGradeScheduler.class);

    @Scheduled(fixedDelay = 5000)
    public void createRandomGrades() {
        List<Long> studentIds = studentRepository.getAllIds();
        List<Long> subjectIds = subjectRepository.getAllIds();

        Long studentId = studentIds.get(random.nextInt(studentIds.size()));
        Long subjectId = subjectIds.get(random.nextInt(subjectIds.size()));
        Short semester = (short)(random.nextInt(9)+1);
        Short value = (short)random.nextInt(100);

        log.info("Запланированная случайная оценка для случайного студента. studentId={}, subjectId={}, semester={}, value={}",
                studentId, subjectId, semester, value);

        if (!gradeRepository.existsByStudentIdAndSubjectIdAndSemester(studentId, subjectId, semester)){
            gradesService.create(new CreateGradeDto(studentId, subjectId, semester, value));
        }
        else {
            gradesService.update(new EntityGradeDto(gradeRepository.getByStudentIdAndSubjectIdAndSemester(studentId, subjectId, semester).getId(),
                    studentId, subjectId, semester, value));
        }
    }
}
