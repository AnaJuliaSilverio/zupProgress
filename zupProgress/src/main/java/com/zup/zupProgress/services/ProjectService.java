package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.ProjectDTO;
import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.exceptionHandler.EmailAlreadyExistsException;
import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    public ProjectDTO createProject(ProjectDTO dto){
        if (repository.findByName(dto.getName()).isPresent()){
            throw new EmailAlreadyExistsException("Nome já cadastrado");
        }
        ProjectModel project = modelMapper.map(dto, ProjectModel.class);
        repository.save(project);
        return modelMapper.map(project, ProjectDTO.class);
    }
    public ProjectDTO getByName(String name){
        ProjectModel project = repository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(project, ProjectDTO.class);
    }
    public List<String> studentsNamesByProjectName(String name){
        List<String> studentsNames = repository.findStudentNamesByProjectName(name);
        Collections.sort(studentsNames);
        return studentsNames;
    }
    public List<StudentDTO> studentsByProjectName(String name){
        List<StudentDTO> students = repository.findStudentByProjectName(name)
                .stream().map(studentModel -> modelMapper.map(studentModel, StudentDTO.class)).toList();

        return students;
    }
    public List<String> getAllProjectName(){
        return repository.getAllProjectName();
    }


}
