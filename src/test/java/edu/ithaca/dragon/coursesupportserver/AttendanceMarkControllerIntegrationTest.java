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
public class AttendanceMarkControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AttendanceMarkRepository attendanceMarkRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndGetAttendanceMarks() throws Exception {
        // Clear the database
        attendanceMarkRepository.deleteAll();
        studentRepository.deleteAll();

        // Create and save a student
        Student student = new Student("katie123", "Katie");
        studentRepository.save(student);

        // Create and save attendance marks
        AttendanceMark attendanceMark = new AttendanceMark(student.getNetpass(), "COMP220", 1, "present");
        attendanceMarkRepository.save(attendanceMark);

        // Convert the list to JSON
        String attendanceMarksJson = objectMapper.writeValueAsString(List.of(attendanceMark));

        // POST the attendance marks
        mockMvc.perform(post("/api/attendanceMarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(attendanceMarksJson))
                .andExpect(status().isCreated());

        // GET all attendance marks
        mockMvc.perform(get("/api/attendanceMarks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    public void testFilterAttendanceMarks() throws Exception {
        // Clear the database
        attendanceMarkRepository.deleteAll();
        studentRepository.deleteAll();

        // Create related entities (e.g., students)
        Student student = new Student("katie123", "Katie");
        studentRepository.save(student);

        // Save attendance marks to the database
        AttendanceMark attendanceMark1 = new AttendanceMark(student.getNetpass(), "COMP220", 1, "present");
        AttendanceMark attendanceMark2 = new AttendanceMark(student.getNetpass(), "COMP220", 2, "absent");
        attendanceMarkRepository.saveAll(List.of(attendanceMark1, attendanceMark2));

        // GET attendance marks filtered by courseId
        mockMvc.perform(get("/api/attendanceMarks?courseId=COMP220"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        // GET attendance marks filtered by studentId
        mockMvc.perform(get("/api/attendanceMarks?studentId=katie123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}