package com.zup.zupProgress.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.zupProgress.dto.ProjectDTO;
import com.zup.zupProgress.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception {
        ProjectDTO dto = new ProjectDTO();

        when(projectService.createProject(any(ProjectDTO.class))).thenReturn(dto);

        ResponseEntity<ProjectDTO> responseEntity = projectController.create(dto, UriComponentsBuilder.newInstance());

        assertEquals(ResponseEntity.created(new URI("/project")).body(dto), responseEntity);
    }

    @Test
    void testSearchProject() throws Exception {
        String projectName = "TestProject";
        ProjectDTO dto = new ProjectDTO();

        when(projectService.getByName(projectName)).thenReturn(dto);

        ResponseEntity<ProjectDTO> responseEntity = projectController.searchproject(projectName);

        assertEquals(ResponseEntity.ok(dto), responseEntity);
    }
}
