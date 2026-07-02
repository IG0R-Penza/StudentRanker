package com.example.StudentRanker.controller;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.service.StudentService;
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
@RequestMapping("/api/student")
@Tag(name = "Student API", description = "API для управления студентами")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    @Operation(
            summary = "Получить список всех студентов",
            description = "Возвращает всех студентов в виде списка"
    )
    @ApiResponse(responseCode = "200", description = "Список студентов")
    public ResponseEntity<List<EntityStudentDto>> sendAll() {
        return ResponseEntity.status(200).body(studentService.readAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить данные о студенте",
            description = "Возвращает данные о студенте по id"
    )
    @ApiResponse(responseCode = "200", description = "Данные о студенте")
    @ApiResponse(responseCode = "404", description = "Студент не найден")
    public ResponseEntity<EntityStudentDto> sendById(@PathVariable("id") Long id) {
        EntityStudentDto res = studentService.read(id);
        if (res != null) return ResponseEntity.status(200).body(studentService.read(id));
        else return ResponseEntity.status(404).body(null);
    }

    @PostMapping("/")
    @Operation(
            summary = "Создать студента",
            description = "Создаёт запись о студенте в базе данных"
    )
    @ApiResponse(responseCode = "201", description = "Запись создана")
    @ApiResponse(responseCode = "400", description = "Некорректные данные")
    public ResponseEntity create(@RequestBody @Valid CreateStudentDto createStudentDto) {
        studentService.create(createStudentDto);
        return ResponseEntity.status(201).body(null);
    }

    @PatchMapping("/")
    @Operation(
            summary = "Отредактировать студента",
            description = "Редактирует запись об указанном студенте в соответствие с переданными данными"
    )
    @ApiResponse(responseCode = "204", description = "Изменения применены")
    @ApiResponse(responseCode = "404", description = "Студент не найден")
    @ApiResponse(responseCode = "400", description = "Некорректные данные")
    public ResponseEntity update(@RequestBody @Valid EntityStudentDto entityStudentDto) {
        if (studentService.update(entityStudentDto)) return ResponseEntity.status(204).body(null);
        else return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить данные о студенте",
            description = "Удаляет данные о студенте по id"
    )
    @ApiResponse(responseCode = "204", description = "Данные удалены")
    @ApiResponse(responseCode = "404", description = "Студент не найден")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (studentService.delete(id)) return ResponseEntity.status(204).body(null);
        else return ResponseEntity.status(404).body(null);
    }
}
