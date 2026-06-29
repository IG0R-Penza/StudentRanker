package com.example.StudentRanker.mapper;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentEntity createDtoToModel(CreateStudentDto dto);
    StudentEntity entityDtoToModel(EntityStudentDto dto);

    EntityStudentDto entityToEntityDto(StudentEntity entity);

    List<EntityStudentDto> entitiesToEntityDto(List<StudentEntity> entities);
}
