package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MentorService mentorService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapper modelMapper;

    public StudentDTO save(StudentDTO studentDTO) {
        MentorModel mentor = mentorService.findByName(studentDTO.getMentor());
        ProjectModel project = modelMapper.map(projectService.getByName(studentDTO.getProject()), ProjectModel.class);
        StudentModel studentModel = new StudentModel();
        studentModel.setFkMentor(mentor);
        studentModel.setFkProject(project);

        modelMapper.map(studentDTO,studentModel);
        StudentModel studentSaved = studentRepository.save(studentModel);
        return modelMapper.map(studentSaved, StudentDTO.class);
    }

    public List<StudentModel> findAll() {
        return studentRepository.findAll();
    }

    public StudentDTO findByName(String name) {
        StudentModel studentModel = studentRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException());

        String mentorName = studentModel.getFkMentor().getName();
        String projectName = studentModel.getFkProject().getName();
        StudentDTO studentDTO = modelMapper.map(studentModel, StudentDTO.class);
        studentDTO.setMentor(mentorName);
        studentDTO.setProject(projectName);
        return studentDTO;
    }




}
