package com.zup.zupProgress.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.zupProgress.dto.ChallengeDTO;
import com.zup.zupProgress.services.ChallengeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ChallengeControllerTest {
    @InjectMocks
    private ChallengeController controller;
    @Mock
    private ChallengeService service;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception {
        ChallengeDTO dto = new ChallengeDTO();

        when(service.createChallenge(any(ChallengeDTO.class))).thenReturn(dto);

        ResponseEntity<ChallengeDTO> responseEntity = controller.create(dto, UriComponentsBuilder.newInstance());

        assertEquals(ResponseEntity.created(new URI("/challenge")).body(dto), responseEntity);
    }

    @Test
    void testSearchProject() throws Exception {
        String challengeName = "Desafio";
        ChallengeDTO dto = new ChallengeDTO();

        when(service.getByName(challengeName)).thenReturn(dto);

        ResponseEntity<ChallengeDTO> responseEntity = controller.searchChallenge(challengeName);

        assertEquals(ResponseEntity.ok(dto), responseEntity);
    }

}