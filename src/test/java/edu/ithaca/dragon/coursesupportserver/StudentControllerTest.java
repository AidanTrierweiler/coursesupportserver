package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;
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

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllStudents() throws Exception {
        // Mock data
        List<Student> students = List.of(new Student("katie123", "Katie"), new Student("jose456", "Jose"));
        when(studentRepository.findAll()).thenReturn(students);

        // Perform API call and validate response
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
        // Mock data
        Student student = new Student("katie123", "Katie");
        when(studentRepository.save(org.mockito.ArgumentMatchers.any(Student.class))).thenReturn(student);

        // Perform API call and validate response
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content("{\"netpass\":\"katie123\",\"preferredName\":\"Katie\"}"))
                .andExpect(status().isCreated()) // Expect 201 Created
                .andExpect(jsonPath("$.netpass").value("katie123"))
                .andExpect(jsonPath("$.preferredName").value("Katie"));
    }

    @Test
    void testGetAllStudentsSchemaValidation() throws Exception {
        // Mock data
        List<Student> students = List.of(new Student("katie123", "Katie"), new Student("jose456", "Jose"));
        when(studentRepository.findAll()).thenReturn(students);

        // Perform API call and get response as String
        String jsonResponse = mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Load JSON Schema from resources
        String schemaPath = "schemas/student.schema.json";
        String schemaString = new String(
                Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(schemaPath).toURI())));
        Schema schema = SchemaLoader.load(new JSONObject(schemaString));

        // Validate JSON response
        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            schema.validate(jsonArray.getJSONObject(i)); // Throws exception if validation fails
        }
    }
}