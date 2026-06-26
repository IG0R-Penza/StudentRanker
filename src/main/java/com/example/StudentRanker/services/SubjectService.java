package com.example.StudentRanker.services;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.mappers.SubjectMapper;
import com.example.StudentRanker.models.SubjectModel;
import com.example.StudentRanker.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public void create(CreateSubjectDto subjectDto) {
        subjectRepository.save(subjectMapper.createDtoToModel(subjectDto));
    }

    public List<EntitySubjectDto> readAll() { return subjectMapper.modelsToEntityDto(subjectRepository.findAll()); }

    public EntitySubjectDto read(Long id) {
        if (subjectRepository.existsById(id)) return subjectMapper.modelToEntityDto(subjectRepository.getOne(id));
        else return null;
    }

    public boolean update(EntitySubjectDto entitySubjectDto){
        if (subjectRepository.existsById(entitySubjectDto.getId())) {
            SubjectModel subjectModel = subjectMapper.entityDtoToModel(entitySubjectDto);
            subjectRepository.save(subjectModel);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
