package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.*;
import com.zup.zupProgress.repositories.ChallengeRepository;
import com.zup.zupProgress.repositories.FeedbackAtributesRepository;
import com.zup.zupProgress.repositories.FeedbackRepository;
import com.zup.zupProgress.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private FeedbackAtributesRepository feedbackAtributesRepository;

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO,String challengName,String studentName){
        ChallengeModel challengeModel = challengeRepository.findByTitle(challengName).orElseThrow(()->new EntityNotFoundException("Desafio não encontrado"));
        StudentModel student = studentRepository.findByName(studentName).orElseThrow(()->new EntityNotFoundException("Estudante não encontrado"));
        FeedbackModel feedbackModel = new FeedbackModel();
        feedbackModel.setChallengeModel(challengeModel);
        feedbackModel.setStudentModel(student);
        modelMapper.map(feedbackDTO,feedbackModel);
        feedbackRepository.save(feedbackModel);
        return modelMapper.map(feedbackModel, FeedbackDTO.class);
    }
    public FeedbackDTO createFeedbackForm(FeedbackDTO feedbackDTO,String challengName,String studentEmail){
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
    public Map<String, String> conclusionFeedback(String title, String email) {
        List<String> attributes = feedbackAtributesRepository.getAllFeedbackAtributesNames();
        List<FeedbackModel> mentorFeedback = feedbackRepository.findFeedbacksByChallengeTitleAndType(title, email, TypeOfAssessment.MENTOR_ASSESSMENT);
        List<FeedbackModel> instructorFeedback = feedbackRepository.findFeedbacksByChallengeTitleAndType(title, email, TypeOfAssessment.INSTRUCTOR_EVALUATION);
        List<FeedbackModel> selfFeedback = feedbackRepository.findFeedbacksByChallengeTitleAndType(title, email, TypeOfAssessment.SELF_EVALUATE);
        Map<String, String> conclusion = new HashMap<>();
        if(mentorFeedback.isEmpty() && instructorFeedback.isEmpty() && selfFeedback.isEmpty()){
            return new HashMap<>();
        }else {
            for (String attribute : attributes) {
                Map<String, Long> statusCountMap = Stream.of(mentorFeedback, instructorFeedback, selfFeedback)
                        .flatMap(Collection::stream)
                        .filter(feedback -> feedback.getAtributes().equals(attribute))
                        .collect(Collectors.groupingBy(FeedbackModel::getStatus, Collectors.counting()));

                String mostCommonStatus = statusCountMap.entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue))
                        .map(Map.Entry::getKey)
                        .orElse("Dentro do esperado");

                conclusion.put(attribute, mostCommonStatus);
            }
            return conclusion;
        }

    }




}
