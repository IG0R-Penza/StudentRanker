package com.example.StudentRanker.mappers;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.models.StudentModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentModel createDtoToModel(CreateStudentDto dto);
    StudentModel entityDtoToModel(EntityStudentDto dto);

    EntityStudentDto modelToEntityDto(StudentModel model);

    List<EntityStudentDto> modelsToEntityDto(List<StudentModel> models);
}
