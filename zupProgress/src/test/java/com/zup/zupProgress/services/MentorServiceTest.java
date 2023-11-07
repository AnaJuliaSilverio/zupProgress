package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.MentorDTO;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.repositories.MentorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {
    @Mock
    private MentorRepository mentorRepository;
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private MentorService mentorService;

    @Test
    void whenCreateAMentorShouldReturnMentor(){
        MentorDTO mentorDTO = new MentorDTO("Ana","ana@exemplo.com","Mentor da aluna x", LocalDate.now(),LocalDate.now(),true);
        MentorModel mentorModel = new MentorModel(1L,"Ana","ana@exemplo.com","Mentor da aluna x", LocalDate.now(),LocalDate.now(),true);
        when(mentorRepository.save(any())).thenReturn(mentorModel);
        MentorDTO mentor = mentorService.createMentor(mentorDTO);

        assertEquals("Ana",mentor.getName());
        assertEquals("ana@exemplo.com",mentor.getEmail());
        assertEquals("Mentor da aluna x",mentor.getBio());
        assertEquals(LocalDate.now(),mentor.getStartMentoring());
        assertEquals(LocalDate.now(),mentor.getEndMentoring());
        Assertions.assertTrue(mentor.getTraining());
    }
    @Test
    void shouldReturnAllMentorNames(){
        when(mentorRepository.getAllMentorName()).thenReturn(Collections.singletonList("Elton"));
        List<String> allMentorName = mentorService.getAllMentorName();
        assertEquals("Elton",allMentorName.get(0));
    }
    @Test
    void shouldReturnMentor(){
        MentorModel mentorModel = new MentorModel(1L,"Ana","ana@exemplo.com","Mentor da aluna x", LocalDate.now(),LocalDate.now(),true);
        when(mentorRepository.findByName("Ana")).thenReturn(mentorModel);
        MentorModel mentorModel1 = mentorService.findByName("Ana");
        assertEquals(mentorModel,mentorModel1);
    }

    @Test
    public void testFindAllMentor() {

        List<MentorModel> mentorModel = new ArrayList<>();
        when(mentorRepository.findAll()).thenReturn(mentorModel);

        List<MentorModel> rs = mentorService.findAll();

        assertEquals(mentorModel, rs);
    }
}
