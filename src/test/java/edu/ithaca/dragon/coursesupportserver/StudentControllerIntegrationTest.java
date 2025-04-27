package edu.ithaca.dragon.coursesupportserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use the H2 database for this test
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndGetStudents() throws Exception {
        // Clear the database
        studentRepository.deleteAll();

        // Create and save a student
        Student student = new Student("john123", "John Doe");

        // Convert the student to JSON
        String studentJson = objectMapper.writeValueAsString(student);

        // POST the student
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson))
                .andExpect(status().isCreated()); // Expect 201 Created

        // GET all students
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].netpass").value("john123"))
                .andExpect(jsonPath("$[0].preferredName").value("John Doe"));
    }

    @Test
    public void testFilterStudents() throws Exception {
        // Clear the database
        studentRepository.deleteAll();

        // Save multiple students
        Student student1 = new Student("john123", "John Doe");
        Student student2 = new Student("jane456", "Jane Smith");
        studentRepository.saveAll(List.of(student1, student2));

        // GET students filtered by netpass
        mockMvc.perform(get("/api/students?netpass=john123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.netpass").value("john123")) // Expect only 1 student
                .andExpect(jsonPath("$.preferredName").value("John Doe"));
    }
}