package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.model.FeedbackModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.repositories.ChallengeRepository;
import com.zup.zupProgress.repositories.FeedbackRepository;
import com.zup.zupProgress.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private StudentRepository studentRepository;

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO,String challengName,String studentEmail){
        ChallengeModel challengeModel = challengeRepository.findByTitle(challengName).orElseThrow(()->new EntityNotFoundException("Desafio não encontrado"));
        StudentModel student = studentRepository.findByEmail(studentEmail);
        FeedbackModel feedbackModel = new FeedbackModel();
        feedbackModel.setChallengeModel(challengeModel);
        feedbackModel.setStudentModel(student);
        modelMapper.map(feedbackDTO,feedbackModel);
        feedbackRepository.save(feedbackModel);
        return modelMapper.map(feedbackModel, FeedbackDTO.class);
    }
    public List<FeedbackDTO> findFeedbackByChallengeNameAndType(String challengName, TypeOfAssessment typeOfAssessment,String studentEmail){
        return feedbackRepository.findFeedbackByChallengeNameAndType(challengName,typeOfAssessment,studentEmail)
                .stream().map(feedbackModel -> modelMapper.map(feedbackModel,FeedbackDTO.class)).toList();
    }
}
