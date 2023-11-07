package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.FeedbackAtributesDTO;
import com.zup.zupProgress.services.FeedbackAtributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atributes")
@CrossOrigin("*")
public class FeedbackAtributesController {
    @Autowired
    private FeedbackAtributesService feedbackAtributesService;
    @PostMapping
    public ResponseEntity<FeedbackAtributesDTO> createAtribute(@RequestBody FeedbackAtributesDTO feedbackAtributesDTO){
        FeedbackAtributesDTO atribute = feedbackAtributesService.createAtribute(feedbackAtributesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(atribute);
    }
    @GetMapping
    public List<String> getAllAtributes(){
        return feedbackAtributesService.getAllAtributes();
    }
}
