package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(new Student("katie123", "Katie"), new Student("jose456", "Jose"));
        when(studentRepository.findAll()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].netpass").value("katie123"))
                .andExpect(jsonPath("$[0].preferredName").value("Katie"))
                .andExpect(jsonPath("$[1].netpass").value("jose456"))
                .andExpect(jsonPath("$[1].preferredName").value("Jose"));
    }

    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student("katie123", "Katie");
        // Use ArgumentMatchers.any(Student.class) to allow any Student object
        when(studentRepository.save(org.mockito.ArgumentMatchers.any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content("{\"netpass\":\"katie123\",\"preferredName\":\"Katie\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.netpass").value("katie123"))
                .andExpect(jsonPath("$.preferredName").value("Katie"));
    }
}