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

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCourses() throws Exception {
        List<Course> courses = Arrays.asList(new Course("COMP220", "Data Structures"),
                new Course("COMP172", "Intro to Programming"));
        when(courseRepository.findAll()).thenReturn(courses);

        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].courseId").value("COMP220"))
                .andExpect(jsonPath("$[0].courseName").value("Data Structures"))
                .andExpect(jsonPath("$[1].courseId").value("COMP172"))
                .andExpect(jsonPath("$[1].courseName").value("Intro to Programming"));
    }

    @Test
    void testCreateCourse() throws Exception {
        Course course = new Course("COMP220", "Data Structures");
        when(courseRepository.save(course)).thenReturn(course);

        mockMvc.perform(post("/api/courses")
                .contentType("application/json")
                .content("{\"courseId\":\"COMP220\",\"courseName\":\"Data Structures\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value("COMP220"))
                .andExpect(jsonPath("$.courseName").value("Data Structures"));
    }
}
