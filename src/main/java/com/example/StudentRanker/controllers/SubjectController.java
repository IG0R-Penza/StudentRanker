package com.example.StudentRanker.controllers;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.services.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@Tag(name = "Subject API", description = "API для управления предметами")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) { this.subjectService = subjectService; }

    @GetMapping("/")
    @Operation(
            summary = "Получить список всех предметов",
            description = "Возвращает все предметы в виде списка"
    )
    @ApiResponse(responseCode = "200", description = "Список предметов")
    public ResponseEntity<List<EntitySubjectDto>> sendAll() {
        return ResponseEntity.status(200).body(subjectService.readAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить данные о предмете",
            description = "Возвращает данные о предмете по id"
    )
    @ApiResponse(responseCode = "200", description = "Данные о предмете")
    @ApiResponse(responseCode = "404", description = "Предмет не найден")
    public ResponseEntity<EntitySubjectDto> sendById(@PathVariable("id") Long id) {
        EntitySubjectDto res = subjectService.read(id);
        if (res != null) return ResponseEntity.status(200).body(subjectService.read(id));
        else return ResponseEntity.status(404).body(null);
    }

    @PostMapping("/")
    @Operation(
            summary = "Создать предмет",
            description = "Создаёт запись о предмете в базе данных"
    )
    @ApiResponse(responseCode = "201", description = "Запись создана")
    public ResponseEntity create(@RequestBody @Valid CreateSubjectDto createSubjectDto) {
        subjectService.create(createSubjectDto);
        return ResponseEntity.status(201).body(null);
    }

    @PatchMapping("/")
    @Operation(
            summary = "Отредактировать предмет",
            description = "Редактирует запись об указанном предмете в соответствие с переданными данными"
    )
    @ApiResponse(responseCode = "204", description = "Изменения применены")
    @ApiResponse(responseCode = "404", description = "Предмет не найден")
    public ResponseEntity update(@RequestBody @Valid EntitySubjectDto entitySubjectDto) {
        if (subjectService.update(entitySubjectDto)) return ResponseEntity.status(204).body(null);
        else return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить данные о предмете",
            description = "Удаляет данные о предмете по id"
    )
    @ApiResponse(responseCode = "204", description = "Данные удалены")
    @ApiResponse(responseCode = "404", description = "Предмет не найден")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (subjectService.delete(id)) return ResponseEntity.status(204).body(null);
        else return ResponseEntity.status(404).body(null);
    }
}
