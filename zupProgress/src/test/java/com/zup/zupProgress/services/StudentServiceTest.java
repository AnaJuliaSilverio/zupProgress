package com.zup.zupProgress.services;

import com.zup.zupProgress.dto.StudentDTO;
import com.zup.zupProgress.exceptionHandler.EmailAlreadyExistsException;
import com.zup.zupProgress.model.MentorModel;
import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import com.zup.zupProgress.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private ProjectService projectService;
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
        MentorModel mentorModel = new MentorModel();
        mentorModel.setName("Mentor");
        ProjectModel projectModel = new ProjectModel();
        projectModel.setName("Project");
        studentModel.setFkMentor(mentorModel);
        studentModel.setFkProject(projectModel);
        when(modelMapper.map(studentDTO, StudentModel.class)).thenReturn(studentModel);
        when(studentRepository.save(studentModel)).thenReturn(studentModel);
        when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);
        when(studentRepository.save(any())).thenReturn(studentModel);
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
    void shouldThrowAExceptionWhenEmailAlreadyExists(){
        StudentModel studentModel = new StudentModel();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEmail("teste@exemplo.com");
        when(studentRepository.findByEmail(anyString())).thenReturn(studentModel);
        assertThrows(EmailAlreadyExistsException.class, () -> {
            studentService.save(studentDTO);
        });
    }
    @Test
    public void testFindByName() {
        String name= "Marcela";
        MentorModel mentorModel = new MentorModel();
        mentorModel.setName("Mentor");
        ProjectModel projectModel = new ProjectModel();
        projectModel.setName("Project");
        StudentModel studentModel = new StudentModel();
        studentModel.setFkMentor(mentorModel);
        studentModel.setFkProject(projectModel);

        when(studentRepository.findByName(name)).thenReturn(Optional.of(studentModel));
        StudentDTO studentDTO = new StudentDTO();
        when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);
        StudentDTO foundStudent = studentService.findByName(name);
        assertEquals(studentDTO, foundStudent);
    }

    @Test
    public void shouldThrowAExceptionWhenNameIsWrong(){
        when(studentRepository.findByName("Marcela")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            studentService.findByName("Macela");
        });
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

    @Test
    void testUpdateDTO() {
        StudentModel studentModel = new StudentModel(1L, "Ana", 19, "Uberlandia", "ana@teste.com", "img.jpg", new MentorModel(), "testando",
                "nao", "nenhuma", new ProjectModel(), LocalDate.now());
        StudentDTO studentDTO = new StudentDTO(1L, 19, "testando", "Uberlandia", LocalDate.now(),
                "ana@teste.com", "Estrelas", "Elton", "Ana", "nao", "nenhuma", "img.jpg");
        when(studentRepository.findByEmail("ana@teste.com")).thenReturn(studentModel);
        when(studentRepository.save(any())).thenReturn(studentModel);
        when(modelMapper.map(studentModel, StudentDTO.class)).thenReturn(studentDTO);
        StudentDTO updated = studentService.updateDTO("ana@teste.com", studentDTO);
        assertEquals("Ana", updated.getName());
        assertEquals("ana@teste.com", updated.getEmail());
        assertEquals(19, updated.getAge());

    }
    @Test
    void testDeleteStudent() {
        String email = "student@example.com";
        StudentModel studentModel = new StudentModel();
        when(studentRepository.findByEmail(email)).thenReturn(studentModel);
        studentService.deleteStudent(email);
        verify(studentRepository, times(1)).delete(studentModel);
    }
}
