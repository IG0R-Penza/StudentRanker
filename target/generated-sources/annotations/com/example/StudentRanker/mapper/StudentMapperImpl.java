package com.example.StudentRanker.mapper;

import com.example.StudentRanker.dto.CreateStudentDto;
import com.example.StudentRanker.dto.EntityStudentDto;
import com.example.StudentRanker.entity.StudentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-07T20:49:23+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.17 (Astra)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentEntity createDtoToModel(CreateStudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setSurname( dto.getSurname() );
        studentEntity.setName( dto.getName() );
        studentEntity.setPatronymic( dto.getPatronymic() );
        studentEntity.setGroupName( dto.getGroupName() );

        return studentEntity;
    }

    @Override
    public StudentEntity entityDtoToModel(EntityStudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId( dto.getId() );
        studentEntity.setSurname( dto.getSurname() );
        studentEntity.setName( dto.getName() );
        studentEntity.setPatronymic( dto.getPatronymic() );
        studentEntity.setGroupName( dto.getGroupName() );

        return studentEntity;
    }

    @Override
    public EntityStudentDto entityToEntityDto(StudentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String surname = null;
        String name = null;
        String patronymic = null;
        String groupName = null;

        id = entity.getId();
        surname = entity.getSurname();
        name = entity.getName();
        patronymic = entity.getPatronymic();
        groupName = entity.getGroupName();

        EntityStudentDto entityStudentDto = new EntityStudentDto( id, surname, name, patronymic, groupName );

        return entityStudentDto;
    }

    @Override
    public List<EntityStudentDto> entitiesToEntityDto(List<StudentEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EntityStudentDto> list = new ArrayList<EntityStudentDto>( entities.size() );
        for ( StudentEntity studentEntity : entities ) {
            list.add( entityToEntityDto( studentEntity ) );
        }

        return list;
    }
}
