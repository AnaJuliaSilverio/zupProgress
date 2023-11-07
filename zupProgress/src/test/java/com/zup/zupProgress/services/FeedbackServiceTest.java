package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.model.FeedbackModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.repositories.ChallengeRepository;
import com.zup.zupProgress.repositories.FeedbackAtributesRepository;
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

import java.util.*;

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
    private FeedbackAtributesRepository feedbackAtributesRepository;

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
    void testCreateFeedbackForm(){
        FeedbackDTO feedbackDTO=new FeedbackDTO(1L,TypeOfAssessment.INSTRUCTOR_EVALUATION,"Feedback Tets","Racicinio","Acima do esperado");
        when(challengeRepository.findByTitle("Desafio")).thenReturn(Optional.of(new ChallengeModel()));
        when((studentRepository.findByEmail("teste@gmail.com"))).thenReturn(new StudentModel());
        FeedbackDTO feedback = feedbackService.createFeedbackForm(feedbackDTO, "Desafio", "teste@gmail.com");
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
    @Test
    void testConclusionFeedback() {

        when(feedbackAtributesRepository.getAllFeedbackAtributesNames())
                .thenReturn(Arrays.asList("Attribute1", "Attribute2"));
        ChallengeModel challengeModel = new ChallengeModel(1L, "Desafio 1");


        FeedbackModel mentorFeedback = new FeedbackModel(1L,TypeOfAssessment.MENTOR_ASSESSMENT,"descrição do feedback", "Attribute1", "status1",challengeModel,new StudentModel());
        FeedbackModel instructorFeedback1 = new FeedbackModel(1L,TypeOfAssessment.INSTRUCTOR_EVALUATION, "descrição do feedback","Attribute1", "status3",challengeModel,new StudentModel());
        FeedbackModel selfFeedback1 = new FeedbackModel(1L,TypeOfAssessment.SELF_EVALUATE,"descrição do feedback", "Attribute1","status1",challengeModel,new StudentModel());

        when(feedbackRepository.findFeedbacksByChallengeTitleAndType("TestChallenge", "test@email.com", TypeOfAssessment.MENTOR_ASSESSMENT))
                .thenReturn(List.of(mentorFeedback));
        when(feedbackRepository.findFeedbacksByChallengeTitleAndType("TestChallenge", "test@email.com", TypeOfAssessment.INSTRUCTOR_EVALUATION))
                .thenReturn(Collections.singletonList(instructorFeedback1));
        when(feedbackRepository.findFeedbacksByChallengeTitleAndType("TestChallenge", "test@email.com", TypeOfAssessment.SELF_EVALUATE))
                .thenReturn(Collections.singletonList(selfFeedback1));


        Map<String, String> conclusion = feedbackService.conclusionFeedback("TestChallenge", "test@email.com");

        assertEquals(2, conclusion.size());
        assertEquals("status1", conclusion.get("Attribute1"));
        assertEquals("dentro-esperado", conclusion.get("Attribute2"));


    }

}