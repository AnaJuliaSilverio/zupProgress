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
@CrossOrigin("*")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO newStudent = studentService.save(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable(value = "email") String email) {
        StudentDTO studentDTO = studentService.findByEmail(email);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentModel>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

    @PutMapping("/{studentEmail}")
    public ResponseEntity<Object> updateStudent(@PathVariable(value = "studentEmail") String email, @RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO dto = studentService.updateDTO(email, studentDTO);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{studentEmail}")
    public ResponseEntity deleteStudent(@PathVariable(value = "studentEmail") String studentEmail){
        studentService.deleteStudent(studentEmail);
        return ResponseEntity.ok().build();
    }


}
