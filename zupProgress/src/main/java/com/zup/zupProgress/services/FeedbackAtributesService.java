package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.FeedbackAtributesDTO;
import com.zup.zupProgress.model.FeedbackAtributesModel;
import com.zup.zupProgress.repositories.FeedbackAtributesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackAtributesService {
    @Autowired
    private FeedbackAtributesRepository feedbackAtributesRepository;
    @Autowired
    private ModelMapper modelMapper;

    public FeedbackAtributesDTO createAtribute(FeedbackAtributesDTO feedbackAtributesDTO){
        FeedbackAtributesModel feedbackAtributesModel = new FeedbackAtributesModel();
        modelMapper.map(feedbackAtributesDTO,feedbackAtributesModel);
        FeedbackAtributesModel saved = feedbackAtributesRepository.save(feedbackAtributesModel);
        return modelMapper.map(saved, FeedbackAtributesDTO.class);
    }
    public List<String> getAllAtributes(){
        return feedbackAtributesRepository.getAllFeedbackAtributesNames();
    }
}
