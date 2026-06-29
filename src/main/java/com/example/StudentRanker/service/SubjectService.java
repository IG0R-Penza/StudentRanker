package com.example.StudentRanker.service;

import com.example.StudentRanker.dto.CreateSubjectDto;
import com.example.StudentRanker.dto.EntitySubjectDto;
import com.example.StudentRanker.mapper.SubjectMapper;
import com.example.StudentRanker.entity.SubjectEntity;
import com.example.StudentRanker.repository.SubjectRepository;
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

    public List<EntitySubjectDto> readAll() { return subjectMapper.entitiesToEntityDto(subjectRepository.findAll()); }

    public EntitySubjectDto read(Long id) {
        if (subjectRepository.existsById(id)) return subjectMapper.entityToEntityDto(subjectRepository.getReferenceById(id));
        else return null;
    }

    public boolean update(EntitySubjectDto entitySubjectDto){
        if (subjectRepository.existsById(entitySubjectDto.getId())) {
            SubjectEntity subjectEntity = subjectMapper.entityDtoToModel(entitySubjectDto);
            subjectRepository.save(subjectEntity);
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
