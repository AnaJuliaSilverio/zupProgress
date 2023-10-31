package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.ProjectDTO;
import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.services.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin("*")
public class ProjectController {
    @Autowired
    private ProjectService service;
    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody @Valid ProjectDTO dto, UriComponentsBuilder uriBuilder){
        ProjectDTO project = service.createProject(dto);
        URI address = uriBuilder.path("/project").buildAndExpand().toUri();
        return ResponseEntity.created(address).body(project);
    }

    @GetMapping("/{projectName}")
    public List<String> getStudentsNamesByProject(@PathVariable(value = "projectName") String projectName){
        return service.studentsNamesByProjectName(projectName);
    }
    @GetMapping
    public List<String> getAllProjectName(){
        return service.getAllProjectName();
    }

    @GetMapping("/get-students/{projectName}")
    public List<StudentDTO> getStudentsByProject(@PathVariable(value = "projectName") String projectName){
        return service.studentsByProjectName(projectName);
    }
}
