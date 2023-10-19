package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.StudentDTO;
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
    private ModelMapper modelMapper;

    public StudentDTO save(StudentDTO studentDTO) {
        StudentModel studentModel = modelMapper.map(studentDTO, StudentModel.class);
        StudentModel studentSaved = studentRepository.save(studentModel);
        return modelMapper.map(studentSaved, StudentDTO.class);
    }

    public List<StudentModel> findAll() {
        return studentRepository.findAll();
    }

    public StudentDTO findByName(String name) {
        StudentModel studentModel = studentRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(studentModel, StudentDTO.class);
    }




}
