package com.example.StudentRanker.mapper;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.entity.SubjectEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-29T19:16:42+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.17 (Astra)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectEntity createDtoToModel(CreateSubjectDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setName( dto.getName() );

        return subjectEntity;
    }

    @Override
    public SubjectEntity entityDtoToModel(EntitySubjectDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setId( dto.getId() );
        subjectEntity.setName( dto.getName() );

        return subjectEntity;
    }

    @Override
    public EntitySubjectDto entityToEntityDto(SubjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        EntitySubjectDto entitySubjectDto = new EntitySubjectDto( id, name );

        return entitySubjectDto;
    }

    @Override
    public List<EntitySubjectDto> entitiesToEntityDto(List<SubjectEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EntitySubjectDto> list = new ArrayList<EntitySubjectDto>( entities.size() );
        for ( SubjectEntity subjectEntity : entities ) {
            list.add( entityToEntityDto( subjectEntity ) );
        }

        return list;
    }
}
