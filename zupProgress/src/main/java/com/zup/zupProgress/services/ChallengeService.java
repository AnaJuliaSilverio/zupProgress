package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.ChallengeDTO;
import com.zup.zupProgress.model.Assessment;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.repositories.ChallengeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ChallengeRepository repository;

    public ChallengeDTO createChallenge(ChallengeDTO dto){
        ChallengeModel challenge = modelMapper.map(dto, ChallengeModel.class);
        repository.save(challenge);
        return modelMapper.map(challenge, ChallengeDTO.class);
    }
    public ChallengeDTO getByName(String name){
        ChallengeModel challenge = repository.findByTitle(name)
                .orElseThrow(()-> new EntityNotFoundException());
        return modelMapper.map(challenge, ChallengeDTO.class);

    }
}
