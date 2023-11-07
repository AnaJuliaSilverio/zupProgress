package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackAtributesDTO;
import com.zup.zupProgress.model.FeedbackAtributesModel;
import com.zup.zupProgress.repositories.FeedbackAtributesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackAtributesServiceTest {
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private FeedbackAtributesRepository feedbackAtributesRepository;
    @InjectMocks
    private FeedbackAtributesService feedbackAtributesService;

    @Test
    void testCreateAtribute(){
        FeedbackAtributesModel feedbackAtributesModel = new FeedbackAtributesModel();
        feedbackAtributesModel.setAtributes("Orientação a objetos");
        FeedbackAtributesDTO feedbackAtributesDTO = new FeedbackAtributesDTO();
        feedbackAtributesDTO.setAtributes("Orientação a objetos");
        when(feedbackAtributesRepository.save(any())).thenReturn(feedbackAtributesModel);
        FeedbackAtributesDTO atribute = feedbackAtributesService.createAtribute(feedbackAtributesDTO);
        assertEquals("Orientação a objetos",atribute.getAtributes());
    }

    @Test
    void testGetAllAtributes(){
        when(feedbackAtributesRepository.getAllFeedbackAtributesNames()).thenReturn(Collections.singletonList("Orientação a objetos"));
        List<String> allAtributes = feedbackAtributesService.getAllAtributes();
        assertEquals("Orientação a objetos",allAtributes.get(0));
    }
}
