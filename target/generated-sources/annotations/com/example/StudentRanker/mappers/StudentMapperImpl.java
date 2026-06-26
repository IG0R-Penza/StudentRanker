package com.example.StudentRanker.mappers;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.models.StudentModel;
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
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentModel createDtoToModel(CreateStudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentModel studentModel = new StudentModel();

        studentModel.setSurname( dto.getSurname() );
        studentModel.setName( dto.getName() );
        studentModel.setPatronymic( dto.getPatronymic() );
        studentModel.setGroup_name( dto.getGroup_name() );

        return studentModel;
    }

    @Override
    public StudentModel entityDtoToModel(EntityStudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentModel studentModel = new StudentModel();

        studentModel.setId( dto.getId() );
        studentModel.setSurname( dto.getSurname() );
        studentModel.setName( dto.getName() );
        studentModel.setPatronymic( dto.getPatronymic() );
        studentModel.setGroup_name( dto.getGroup_name() );

        return studentModel;
    }

    @Override
    public EntityStudentDto modelToEntityDto(StudentModel model) {
        if ( model == null ) {
            return null;
        }

        Long id = null;
        String surname = null;
        String name = null;
        String patronymic = null;
        String group_name = null;

        id = model.getId();
        surname = model.getSurname();
        name = model.getName();
        patronymic = model.getPatronymic();
        group_name = model.getGroup_name();

        EntityStudentDto entityStudentDto = new EntityStudentDto( id, surname, name, patronymic, group_name );

        return entityStudentDto;
    }

    @Override
    public List<EntityStudentDto> modelsToEntityDto(List<StudentModel> models) {
        if ( models == null ) {
            return null;
        }

        List<EntityStudentDto> list = new ArrayList<EntityStudentDto>( models.size() );
        for ( StudentModel studentModel : models ) {
            list.add( modelToEntityDto( studentModel ) );
        }

        return list;
    }
}
