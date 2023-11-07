package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.InstructorDTO;
import com.zup.zupProgress.model.InstructorModel;
import com.zup.zupProgress.repositories.InstructorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public InstructorDTO createInstructor(InstructorDTO instructorDTO){
        InstructorModel instructorModel = modelMapper.map(instructorDTO,InstructorModel.class);
        InstructorModel saved = instructorRepository.save(instructorModel);
        return modelMapper.map(saved, InstructorDTO.class);
    }

    public List<InstructorDTO> getAllInstructor(){
        return instructorRepository.findAll().stream()
                .map(instructorModel -> modelMapper.map(instructorModel, InstructorDTO.class)).toList();
    }
}
