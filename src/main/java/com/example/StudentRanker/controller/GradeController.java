package com.example.StudentRanker.controller;

import com.example.StudentRanker.dto.*;
import com.example.StudentRanker.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grade")
@Tag(name = "Grade API", description = "API для управления оценками")
public class GradeController {
    private final GradeService gradeService;

    @PostMapping("/")
    @Operation(
            summary = "Поставить оценку",
            description = "Создаёт запись об оценке в базе данных"
    )
    @ApiResponse(responseCode = "201", description = "Оценка создана")
    @ApiResponse(responseCode = "400", description = "Некорректные данные")
    public ResponseEntity create(@RequestBody @Valid CreateGradeDto createGradeDto){
        if (gradeService.create(createGradeDto)) return ResponseEntity.status(201).body(null);
        else return ResponseEntity.status(400).body(null);
    }

    @PatchMapping("/")
    @Operation(
            summary = "Отредактировать оценку",
            description = "Редактирует запись об указанной оценке в соответствие с переданными данными"
    )
    @ApiResponse(responseCode = "204", description = "Изменения применены")
    @ApiResponse(responseCode = "404", description = "Оценка не найдена")
    @ApiResponse(responseCode = "400", description = "Некорректные данные")
    public ResponseEntity update(@RequestBody @Valid EntityGradeDto entityGradeDto) {
        if (gradeService.update(entityGradeDto)) return ResponseEntity.status(204).body(null);
        else return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить оценку",
            description = "Удаляет запись об указанной оценке"
    )
    @ApiResponse(responseCode = "204", description = "Изменения применены")
    @ApiResponse(responseCode = "404", description = "Оценка не найдена")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (gradeService.delete(id)) return ResponseEntity.status(204).body(null);
        else return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/student")
    @Operation(
            summary = "Данные об оценках студента",
            description = "Возвращает список студентов с заданным ФИО, указывая для каждого студента ID, номер группы, все его оценки и средний балл"
    )
    @ApiResponse(responseCode = "200", description = "Список студентов с заданным ФИО, указывая для каждого студента ID, номер группы, все его оценки и средний балл")
    @ApiResponse(responseCode = "404", description = "Студенты не найдены или не имеют оценок")
    public ResponseEntity<List<StudentGradeDto>> getGradesByFullName(@RequestParam String surname, @RequestParam String name, @RequestParam(required = false) String patronymic) {
        var res = gradeService.getGradesByFullName(surname, name, patronymic);
        if (!res.isEmpty()) return ResponseEntity.status(200).body(res);
        else return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/rating")
    @Operation(
            summary = "Рейтинг студентов по сумме баллов",
            description = "Возварщает список всех студентов, упорядоченный по сумме баллов"
    )
    @ApiResponse(responseCode = "200", description = "Рейтинг студентов")
    public ResponseEntity<List<StudentRatingItemDto>> getStudentRating() {
        return ResponseEntity.status(200).body(gradeService.getStudentRating());
    }
}
