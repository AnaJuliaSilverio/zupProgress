package com.zup.zupProgress.controllers;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.services.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping("/{challengeName}")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody @Valid FeedbackDTO feedbackDTO, @PathVariable(name = "challengeName") String challengeName){
        FeedbackDTO feedback = feedbackService.createFeedback(feedbackDTO, challengeName);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }
    @GetMapping("/{challengeName}/{type}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacks( @PathVariable(name = "challengeName") String challengeName, @PathVariable(name = "type") String type){
        TypeOfAssessment typeOfAssessment = TypeOfAssessment.valueOf(type);
        List<FeedbackDTO> feedbackByChallengeNameAndType = feedbackService.findFeedbackByChallengeNameAndType(challengeName, typeOfAssessment);

        return ResponseEntity.ok(feedbackByChallengeNameAndType);

    }
}
