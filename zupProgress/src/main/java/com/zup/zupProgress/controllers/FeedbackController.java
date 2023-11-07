package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.services.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping("/{challengeName}/{name}")
    public ResponseEntity<List<FeedbackDTO>> createFeedbackList(@RequestBody List<FeedbackDTO> feedbackList, @PathVariable(name = "challengeName") String challengeName, @PathVariable(name = "name") String name) {
        List<FeedbackDTO> savedFeedbackList = new ArrayList<>();
        feedbackList.forEach(feedbackDTO -> {
            FeedbackDTO savedFeedback = feedbackService.createFeedback(feedbackDTO, challengeName, name);
            savedFeedbackList.add(savedFeedback);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedbackList);
    }
    @PostMapping("/forms/{challengeName}/{studentEmail}")
    public ResponseEntity<List<FeedbackDTO>> createFeedbackListStudent(@RequestBody List<FeedbackDTO> feedbackList, @PathVariable(name = "challengeName") String challengeName, @PathVariable(value = "studentEmail") String studentEmail) {
        List<FeedbackDTO> savedFeedbackList = new ArrayList<>();
        feedbackList.forEach(feedbackDTO -> {
            FeedbackDTO savedFeedback = feedbackService.createFeedbackForm(feedbackDTO, challengeName, studentEmail);
            savedFeedbackList.add(savedFeedback);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFeedbackList);
    }
    @GetMapping("/{challengeName}/{type}/{studentEmail}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacks( @PathVariable(name = "challengeName") String challengeName,
                                                           @PathVariable(name = "type") String type,@PathVariable(value = "studentEmail") String studentEmail){
        TypeOfAssessment typeOfAssessment = TypeOfAssessment.valueOf(type);
        List<FeedbackDTO> feedbackByChallengeNameAndType = feedbackService.findFeedbackByChallengeNameAndType(challengeName, typeOfAssessment,studentEmail);

        return ResponseEntity.ok(feedbackByChallengeNameAndType);
    }
    @GetMapping("/conclusion/{challengeName}/{studentEmail}")
    public Map<String,String> getConclusion(@PathVariable(name = "challengeName") String challengeName, @PathVariable(value = "studentEmail") String studentEmail){
        return feedbackService.conclusionFeedback(challengeName,studentEmail);
    }
}
