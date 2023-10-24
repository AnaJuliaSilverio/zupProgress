package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackDTO;
import com.zup.zupProgress.model.ChallengeModel;
import com.zup.zupProgress.model.FeedbackModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import com.zup.zupProgress.repositories.ChallengeRepository;
import com.zup.zupProgress.repositories.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FeedbackServiceTest{

    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private ChallengeRepository challengeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindFeedbackByChallengeNameAndType() {
        ChallengeModel challengeModel = new ChallengeModel();
        when(challengeRepository.findByTitle("DesafioTest")).thenReturn(Optional.of(challengeModel));

        FeedbackModel feedbackModel1 = new FeedbackModel();
        FeedbackModel feedbackModel2 = new FeedbackModel();
        List<FeedbackModel> feedbackModels = Arrays.asList(feedbackModel1, feedbackModel2);
        when(feedbackRepository.findFeedbackByChallengeNameAndType("DesafioTest", TypeOfAssessment.MENTOR_ASSESSMENT)).thenReturn(feedbackModels);

        FeedbackDTO feedbackDTO1 = new FeedbackDTO();
        FeedbackDTO feedbackDTO2 = new FeedbackDTO();
        when(modelMapper.map(feedbackModel1, FeedbackDTO.class)).thenReturn(feedbackDTO1);
        when(modelMapper.map(feedbackModel2, FeedbackDTO.class)).thenReturn(feedbackDTO2);

        List<FeedbackDTO> result = feedbackService.findFeedbackByChallengeNameAndType("DesafioTest", TypeOfAssessment.MENTOR_ASSESSMENT);

        assertEquals(2, result.size());
        assertEquals(feedbackDTO1, result.get(0));
        assertEquals(feedbackDTO2, result.get(1));
    }
    @Test
    void testCreateFeedback_ChallengeNotFound() {
        FeedbackDTO inputDTO = new FeedbackDTO();
        inputDTO.setType(TypeOfAssessment.CONCLUSION);
        inputDTO.setDescription("Test Description");
        inputDTO.setAtributes("Test Attributes");
        inputDTO.setStatus("Test Status");

        when(challengeRepository.findByTitle("Teste")).thenThrow(new EntityNotFoundException("Desafio não encontrado"));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> feedbackService.createFeedback(inputDTO, "Teste"));

        assertEquals("Desafio não encontrado", exception.getMessage());
    }
}