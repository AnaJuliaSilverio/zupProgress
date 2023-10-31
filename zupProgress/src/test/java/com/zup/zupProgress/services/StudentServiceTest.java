package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private MentorService mentorService;

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
        when(modelMapper.map(studentDTO, StudentModel.class)).thenReturn(studentModel);
        when(studentRepository.save(studentModel)).thenReturn(studentModel);
        when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);


        StudentDTO savedStudent = studentService.save(studentDTO);


        assertEquals(studentDTO, savedStudent);
    }

    @Test
    public void testFindAllStudents() {

        List<StudentModel> studentListModel = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(studentListModel);

        List<StudentModel> rs = studentService.findAll();

        assertEquals(studentListModel, rs);
    }

    @Test
    public void testFindByName() {
        String name = "Marcela";
        StudentModel studentModel = new StudentModel();
        when(studentRepository.findByName(name)).thenReturn(Optional.of(studentModel));
        StudentDTO studentDTO = new StudentDTO();
        when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);
        StudentDTO foundStudent = studentService.findByName(name);
        assertEquals(studentDTO, foundStudent);
    }
    @Test
    public void testFindByEmail() {
        String email = "Marcela@gmail.com";
        MentorModel mentorModel = new MentorModel();
        mentorModel.setName("Mentor");
        ProjectModel projectModel = new ProjectModel();
        projectModel.setName("Project");
        StudentModel studentModel = new StudentModel();
        studentModel.setFkMentor(mentorModel);
        studentModel.setFkProject(projectModel);
        when(studentRepository.findByEmail(email)).thenReturn(studentModel);

        StudentDTO studentDTO = new StudentDTO();
        when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);
        StudentDTO foundStudent = studentService.findByEmail(email);
        assertEquals(studentDTO, foundStudent);
    }



}
