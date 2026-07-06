package com.example.StudentRanker.mapper;

import com.example.StudentRanker.dto.CreateGradeDto;
import com.example.StudentRanker.dto.EntityGradeDto;
import com.example.StudentRanker.entity.GradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GradeMapper {
    EntityGradeDto entityToEntityDto(GradeEntity entity);
    List<EntityGradeDto> entitiesToEntityDto(List<GradeEntity> entities);

    GradeEntity createDtoToEntity(CreateGradeDto dto);
    GradeEntity entityDtoToEntity(EntityGradeDto dto);
}
