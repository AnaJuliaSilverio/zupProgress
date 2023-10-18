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
}
