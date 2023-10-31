package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.model.FeedbackModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.repositories.ChallengeRepository;
import com.zup.zupProgress.repositories.FeedbackRepository;
import com.zup.zupProgress.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest{

    @InjectMocks
    private FeedbackService feedbackService;

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private ChallengeRepository challengeRepository;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateFeedback(){
        FeedbackDTO feedbackDTO=new FeedbackDTO(1L,TypeOfAssessment.INSTRUCTOR_EVALUATION,"Feedback Tets","Racicinio","Acima do esperado");
        when(challengeRepository.findByTitle("Desafio")).thenReturn(Optional.of(new ChallengeModel()));
        when((studentRepository.findByName("teste"))).thenReturn(Optional.of(new StudentModel()));
        FeedbackDTO feedback = feedbackService.createFeedback(feedbackDTO, "Desafio", "teste");
        assertEquals(TypeOfAssessment.INSTRUCTOR_EVALUATION,feedback.getType());
    }
    @Test
    void shouldThrowAExceptionChallengeNotFound(){
        FeedbackDTO feedbackDTO=new FeedbackDTO(1L,TypeOfAssessment.INSTRUCTOR_EVALUATION,"Feedback Tets","Racicinio","Acima do esperado");
        when(challengeRepository.findByTitle("Desafio")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->feedbackService.createFeedback(feedbackDTO, "Desafio", "teste@gmail.com"));

    }

    @Test
    void testFindFeedbackByChallengeNameAndType(){
        FeedbackModel feedbackModel = new FeedbackModel(1L,TypeOfAssessment.INSTRUCTOR_EVALUATION,"Feedback Tets","Racicinio","Acima do esperado",new ChallengeModel(),new StudentModel());

        when(feedbackRepository.findFeedbackByChallengeNameAndType("Desafio",TypeOfAssessment.INSTRUCTOR_EVALUATION,"Ana@gmail.com")).thenReturn(Collections.singletonList(feedbackModel));
        List<FeedbackDTO> feedback = feedbackService.findFeedbackByChallengeNameAndType("Desafio", TypeOfAssessment.INSTRUCTOR_EVALUATION, "Ana@gmail.com");
        assertEquals(TypeOfAssessment.INSTRUCTOR_EVALUATION,feedback.get(0).getType());
        assertEquals("Feedback Tets",feedback.get(0).getDescription());
        assertEquals("Racicinio",feedback.get(0).getAtributes());
        assertEquals("Acima do esperado",feedback.get(0).getStatus());
    }

}