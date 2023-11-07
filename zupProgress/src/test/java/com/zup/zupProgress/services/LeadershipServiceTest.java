package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.LeadershipDTO;
import com.zup.zupProgress.model.LeadershipModel;
import com.zup.zupProgress.repositories.LeadershipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeadershipServiceTest {
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private LeadershipRepository leadershipRepository;
    @InjectMocks
    private LeadershipService leadershipService;

    @Test
    void testCreateLeadership(){
        LeadershipDTO leadershipDTO = new LeadershipDTO();
        leadershipDTO.setName("Teste Model");
        LeadershipModel leadershipModel = new LeadershipModel();
        leadershipModel.setName("Teste Model");
        when(leadershipRepository.save(any())).thenReturn(leadershipModel);
        LeadershipDTO leadership = leadershipService.createLeadership(leadershipDTO);
        assertEquals("Teste Model",leadership.getName());
    }

    @Test
    void testGetAllLeadership(){
        LeadershipModel leadershipModel = new LeadershipModel(1L,"Ana","ana@email.com");
        when(leadershipRepository.findAll()).thenReturn(Collections.singletonList(leadershipModel));
        List<LeadershipDTO> allLeadership = leadershipService.getAllLeadership();
        assertEquals("Ana",allLeadership.get(0).getName());
        assertEquals("ana@email.com",allLeadership.get(0).getEmail());
    }

}
