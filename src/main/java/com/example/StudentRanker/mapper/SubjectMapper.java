package com.example.StudentRanker.mapper;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper  {
    SubjectEntity createDtoToModel(CreateSubjectDto dto);
    SubjectEntity entityDtoToModel(EntitySubjectDto dto);

    EntitySubjectDto entityToEntityDto(SubjectEntity entity);

    List<EntitySubjectDto> entitiesToEntityDto(List<SubjectEntity> entities);
}
