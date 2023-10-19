package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStudent() {

        StudentDTO studentDTO = new StudentDTO();
        StudentModel studentModel = new StudentModel();
        Mockito.when(modelMapper.map(studentDTO, StudentModel.class)).thenReturn(studentModel);
        Mockito.when(studentRepository.save(studentModel)).thenReturn(studentModel);
        Mockito.when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);


        StudentDTO savedStudent = studentService.save(studentDTO);


        assertEquals(studentDTO, savedStudent);
    }

    @Test
    public void testFindAllStudents() {

        List<StudentModel> studentListModel = new ArrayList<>();
        Mockito.when(studentRepository.findAll()).thenReturn(studentListModel);

        List<StudentModel> rs = studentService.findAll();

        assertEquals(studentListModel, rs);
    }

    @Test
    public void testFindByName() {

        String name = "Marcela";
        StudentModel studentModel = new StudentModel();
        Mockito.when(studentRepository.findByName(name)).thenReturn(Optional.of(studentModel));
        StudentDTO studentDTO = new StudentDTO();
        Mockito.when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO foundStudent = studentService.findByName(name);


        assertEquals(studentDTO, foundStudent);
    }


}
