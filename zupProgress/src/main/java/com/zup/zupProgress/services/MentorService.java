package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.MentorDTO;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.repositories.MentorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MentorDTO createMentor(MentorDTO mentorDTO){
        MentorModel mentorModel = modelMapper.map(mentorDTO, MentorModel.class);

        MentorModel mentorSaved = mentorRepository.save(mentorModel);
        return modelMapper.map(mentorSaved, MentorDTO.class);
    }
}
