package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AttendanceMarkController.class)
public class AttendanceMarkContollerTest {

    @MockBean
    private AttendanceMarkRepository attendanceMarkRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllAttendanceMarks() throws Exception {
        // findAll
        when(attendanceMarkRepository.findAll()).thenReturn(AttendanceMarkRespositoryExamples.basicTestRepoList());
        mockMvc.perform(get("/api/attendanceMarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(18));

        // findByCourseId
        List<AttendanceMark> marksFor220 = AttendanceMarkRespositoryExamples.basicTestRepoList().stream()
                .filter(mark -> mark.getCourseId().equals("COMP220")).collect(Collectors.toList());
        when(attendanceMarkRepository.findByCourseId("COMP220")).thenReturn(marksFor220);
        mockMvc.perform(get("/api/attendanceMarks?courseId=COMP220"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(10));

        // findByStudentId
        List<AttendanceMark> marksForKatie = AttendanceMarkRespositoryExamples.basicTestRepoList().stream()
                .filter(mark -> mark.getStudentId().equals("Katie")).collect(Collectors.toList());
        when(attendanceMarkRepository.findByStudentId("Katie")).thenReturn(marksForKatie);
        mockMvc.perform(get("/api/attendanceMarks?studentId=Katie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(4));

        // findByBoth
        mockMvc.perform(get("/api/attendanceMarks?studentId=Katie&courseId=COMP220"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetAllAttendanceMarksSchemaValidation() throws Exception {
        // New test for schema validation
        List<AttendanceMark> attendanceMarks = AttendanceMarkRespositoryExamples.basicTestRepoList();
        when(attendanceMarkRepository.findAll()).thenReturn(attendanceMarks);

        String jsonResponse = mockMvc.perform(get("/api/attendanceMarks"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String schemaPath = "schemas/attendanceMark.schema.json";
        String schemaString = new String(
                Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(schemaPath).toURI())));
        Schema schema = SchemaLoader.load(new JSONObject(schemaString));

        JSONArray jsonArray = new JSONArray(jsonResponse);
        for (int i = 0; i < jsonArray.length(); i++) {
            schema.validate(jsonArray.getJSONObject(i)); // Throws exception if validation fails
        }
    }

    @Test
    void testRecordAttendance() {

    }
}
