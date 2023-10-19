package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO newStudent = studentService.save(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @GetMapping("/{name}")
    public ResponseEntity<StudentDTO> getStudentByName(@PathVariable(value = "name") String name) {
        StudentDTO studentDTO = studentService.findByName(name);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentModel>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @PutMapping("/{name}")
    public ResponseEntity<Object> updateStudent(@PathVariable(value = "name") String name, @RequestBody @Valid StudentDTO studentDTO) {

        Optional<StudentDTO> studentModelOptional = Optional.ofNullable(studentService.findByName(name));

        var studentModel = studentModelOptional.get();

        studentModel.setName(studentDTO.getName());
        studentModel.setAge(studentDTO.getAge());
        studentModel.setCity(studentDTO.getCity());
        studentModel.setEmail(studentDTO.getEmail());
        studentModel.setImage(studentDTO.getImage());
        studentModel.setBio(studentDTO.getBio());
        studentModel.setPcd(studentDTO.getPcd());

        studentModel.setTypeOfDisability(studentDTO.getTypeOfDisability());
        studentModel.setContract_end(studentDTO.getContract_end());

        return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentModel));
    }

}
