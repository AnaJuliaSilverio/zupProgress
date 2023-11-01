package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.exceptionHandler.EmailAlreadyExistsException;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (studentRepository.findByEmail(studentDTO.getEmail())!=null){
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }
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
    public StudentDTO findByEmail(String email){
        StudentModel studentModel = studentRepository.findByEmail(email);
        String mentorName = studentModel.getFkMentor().getName();
        String projectName = studentModel.getFkProject().getName();
        StudentDTO studentDTO = modelMapper.map(studentModel, StudentDTO.class);
        studentDTO.setMentor(mentorName);
        studentDTO.setProject(projectName);
        return studentDTO;
    }

    public StudentDTO updateDTO(String email,StudentDTO studentDTO){
        StudentModel studentModel = studentRepository.findByEmail(email);


        studentModel.setName(studentDTO.getName());
        studentModel.setAge(studentDTO.getAge());
        studentModel.setCity(studentDTO.getCity());
        studentModel.setEmail(studentDTO.getEmail());
        studentModel.setImage(studentDTO.getImage());
        studentModel.setBio(studentDTO.getBio());
        studentModel.setPcd(studentDTO.getPcd());

        studentModel.setTypeOfDisability(studentDTO.getTypeOfDisability());
        studentModel.setContract_end(studentDTO.getContract_end());

        StudentModel saved = studentRepository.save(studentModel);

         return modelMapper.map(saved,StudentDTO.class);
    }

    public void deleteStudent(String email){
        StudentModel studentModel = studentRepository.findByEmail(email);
        studentRepository.delete(studentModel);
    }



}
