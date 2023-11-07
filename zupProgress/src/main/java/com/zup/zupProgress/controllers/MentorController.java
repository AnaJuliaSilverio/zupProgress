package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.MentorDTO;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.services.MentorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentor")
@CrossOrigin("*")
public class MentorController {
    @Autowired
    private MentorService mentorService;
    @PostMapping
    public ResponseEntity<MentorDTO> createMentor(@RequestBody @Valid MentorDTO mentorDTO){
        MentorDTO mentor = mentorService.createMentor(mentorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentor);
    }
    @GetMapping
    public List<String> getAllMentorName(){
        return mentorService.getAllMentorName();
    }

    @GetMapping
    public ResponseEntity<List<MentorModel>> getAllMentor() {
        return ResponseEntity.status(HttpStatus.OK).body(mentorService.findAll());
    }


}

