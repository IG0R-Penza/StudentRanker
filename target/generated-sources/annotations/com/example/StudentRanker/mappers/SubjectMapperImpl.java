package com.example.StudentRanker.mappers;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.models.SubjectModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-26T15:00:37+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.17 (Astra)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectModel createDtoToModel(CreateSubjectDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubjectModel subjectModel = new SubjectModel();

        subjectModel.setName( dto.getName() );

        return subjectModel;
    }

    @Override
    public SubjectModel entityDtoToModel(EntitySubjectDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubjectModel subjectModel = new SubjectModel();

        subjectModel.setId( dto.getId() );
        subjectModel.setName( dto.getName() );

        return subjectModel;
    }

    @Override
    public EntitySubjectDto modelToEntityDto(SubjectModel model) {
        if ( model == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = model.getId();
        name = model.getName();

        EntitySubjectDto entitySubjectDto = new EntitySubjectDto( id, name );

        return entitySubjectDto;
    }

    @Override
    public List<EntitySubjectDto> modelsToEntityDto(List<SubjectModel> models) {
        if ( models == null ) {
            return null;
        }

        List<EntitySubjectDto> list = new ArrayList<EntitySubjectDto>( models.size() );
        for ( SubjectModel subjectModel : models ) {
            list.add( modelToEntityDto( subjectModel ) );
        }

        return list;
    }
}
