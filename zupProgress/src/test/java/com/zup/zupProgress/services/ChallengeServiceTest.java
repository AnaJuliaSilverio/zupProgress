package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.ChallengeDTO;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.repositories.ChallengeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ChallengeServiceTest {

    @Mock
    private ChallengeRepository repository;
    @InjectMocks
    private ChallengeService challengeService;
    @Mock
    private ModelMapper modelMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateProject() {
        ChallengeDTO inputDTO = new ChallengeDTO();
        inputDTO.setTitle("Test desafio");
        ChallengeModel model = new ChallengeModel();
        model.setTitle("Test desafio");

        when(modelMapper.map(inputDTO, ChallengeModel.class)).thenReturn(model);
        when(repository.save(model)).thenReturn(model);
        when(modelMapper.map(model, ChallengeDTO.class)).thenReturn(inputDTO);

        ChallengeDTO resultDTO = challengeService.createChallenge(inputDTO);

        assertEquals(inputDTO.getTitle(), resultDTO.getTitle());
    }

    @Test
    void testGetByName() {
        String challengeName = "Buscar desafio";
        ChallengeModel challenge = new ChallengeModel();
        ChallengeDTO challengeDto = new ChallengeDTO();
        challengeDto.setTitle(challengeName);
        challenge.setTitle(challengeName);

        when(modelMapper.map(eq(challenge), eq(ChallengeDTO.class))).thenReturn(challengeDto);
        when(repository.findByTitle(challengeName)).thenReturn(Optional.of(challenge));
        ChallengeDTO resultDTO = challengeService.getByName(challengeName);
        assertNotNull(resultDTO);
        assertEquals(challengeName, resultDTO.getTitle());
    }

    @Test
    void testGetByNameNotFound() {
        String challengeName = "Desafio nao existente";

        when(repository.findByTitle(challengeName)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            challengeService.getByName(challengeName);
        });
    }

}