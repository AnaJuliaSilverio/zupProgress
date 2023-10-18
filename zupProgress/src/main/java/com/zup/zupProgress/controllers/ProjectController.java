package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.ProjectDTO;
import com.zup.zupProgress.services.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService service;
    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody @Valid ProjectDTO dto, UriComponentsBuilder uriBuilder){
        ProjectDTO project = service.createProject(dto);
        URI address = uriBuilder.path("/project").buildAndExpand().toUri();
        return ResponseEntity.created(address).body(project);
    }
    @GetMapping("/{name}")
    public ResponseEntity<ProjectDTO> searchproject(@PathVariable @NotNull String name){
        ProjectDTO project = service.getByName(name);
        return ResponseEntity.ok(project);
    }
}
