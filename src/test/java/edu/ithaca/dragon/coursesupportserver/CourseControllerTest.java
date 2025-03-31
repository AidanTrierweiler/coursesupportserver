package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
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
        // Use ArgumentMatchers.any(Course.class) so any Course object is matched
        when(courseRepository.save(org.mockito.ArgumentMatchers.any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/api/courses")
                .contentType("application/json")
                .content("{\"courseId\":\"COMP220\",\"courseName\":\"Data Structures\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value("COMP220"))
                .andExpect(jsonPath("$.courseName").value("Data Structures"));
    }

    @Test
    void testGetAllCoursesSchemaValidation() throws Exception {
        // New test for schema validation
        List<Course> courses = List.of(
                new Course("COMP220", "Data Structures"),
                new Course("COMP172", "Intro to Programming"));
        when(courseRepository.findAll()).thenReturn(courses);

        String jsonResponse = mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String schemaPath = "schemas/course.schema.json";
        String schemaString = new String(
                Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(schemaPath).toURI())));
        Schema schema = SchemaLoader.load(new JSONObject(schemaString));

        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            schema.validate(jsonArray.getJSONObject(i)); // Throws exception if validation fails
        }
    }

}
