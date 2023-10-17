package com.zup.zupProgress.services;

import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.repositories.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    public MentorModel createMentor(MentorModel mentorModel){
        return mentorRepository.save(mentorModel);
    }
}
