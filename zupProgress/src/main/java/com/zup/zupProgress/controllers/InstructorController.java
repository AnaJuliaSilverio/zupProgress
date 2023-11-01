package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.InstructorDTO;
import com.zup.zupProgress.services.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody @Valid InstructorDTO instructorDTO){
        InstructorDTO instructor = instructorService.createInstructor(instructorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
    }
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructor(){
        List<InstructorDTO> allInstructor = instructorService.getAllInstructor();
        return ResponseEntity.ok(allInstructor);
    }
}
