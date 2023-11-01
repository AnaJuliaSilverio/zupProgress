package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.LeadershipDTO;
import com.zup.zupProgress.model.LeadershipModel;
import com.zup.zupProgress.repositories.LeadershipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadershipService {
    @Autowired
    private LeadershipRepository leadershipRepository;
    @Autowired
    private ModelMapper modelMapper;

    public LeadershipDTO createLeadership(LeadershipDTO leadershipDTO){
        LeadershipModel leadershipModel = modelMapper.map(leadershipDTO, LeadershipModel.class);
        LeadershipModel saved = leadershipRepository.save(leadershipModel);
        return modelMapper.map(saved, LeadershipDTO.class);
    }
    public List<LeadershipDTO> getAllLeadership(){
        return leadershipRepository.findAll().stream()
                .map(leadershipModel -> modelMapper.map(leadershipModel, LeadershipDTO.class)).toList();
    }
}
