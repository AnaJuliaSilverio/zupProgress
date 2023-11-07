package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.InstructorDTO;
import com.zup.zupProgress.model.InstructorModel;
import com.zup.zupProgress.repositories.InstructorRepository;
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
public class InstructorServiceTest {
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private InstructorService instructorService;
    @Mock
    private InstructorRepository instructorRepository;

    @Test
    void testCreateInstructor(){
        InstructorModel instructorModel = new InstructorModel();
        instructorModel.setName("Carol");
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setName("Carol");
        when(instructorRepository.save(any())).thenReturn(instructorModel);
        InstructorDTO instructor = instructorService.createInstructor(instructorDTO);
        assertEquals("Carol",instructor.getName());
    }
    @Test
    void testGetAllInstructor(){
        InstructorModel instructorModel = new InstructorModel(1L,"Carol","carol@gmail.com");
        when(instructorRepository.findAll()).thenReturn(Collections.singletonList(instructorModel));
        List<InstructorDTO> allInstructor = instructorService.getAllInstructor();
        assertEquals("Carol",allInstructor.get(0).getName());
        assertEquals("carol@gmail.com",allInstructor.get(0).getEmail()) ;

    }
}
