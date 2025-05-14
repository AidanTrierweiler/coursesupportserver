package edu.ithaca.dragon.coursesupportserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use the H2 database for this test
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFilterCourses() throws Exception {
        // Clear the database
        courseRepository.deleteAll();

        // Save multiple courses
        Course course1 = new Course("COMP220", "Data Structures");
        Course course2 = new Course("COMP172", "Introduction to Programming");
        courseRepository.saveAll(List.of(course1, course2));

        // GET courses filtered by courseId
        mockMvc.perform(get("/api/courses?courseId=COMP220"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].courseId").value("COMP220"))
                .andExpect(jsonPath("$[0].courseName").value("Data Structures"));
    }
}