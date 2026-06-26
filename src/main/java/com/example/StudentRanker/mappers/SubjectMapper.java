package com.example.StudentRanker.mappers;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.models.SubjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper  {
    SubjectModel createDtoToModel(CreateSubjectDto dto);
    SubjectModel entityDtoToModel(EntitySubjectDto dto);

    EntitySubjectDto modelToEntityDto(SubjectModel model);

    List<EntitySubjectDto> modelsToEntityDto(List<SubjectModel> models);
}
