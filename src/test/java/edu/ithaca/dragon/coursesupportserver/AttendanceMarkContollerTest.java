package edu.ithaca.dragon.coursesupportserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AttendanceMarkContoller.class)
public class AttendanceMarkContollerTest {
    @MockBean
    private AttendanceMarkRepository attendanceMarkRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper ObjectMapper;
    
    @Test
    void testGetAllAttendanceMarks() throws Exception {
        //findAll
        when(attendanceMarkRepository.findAll()).thenReturn(AttendanceMarkRespositoryExamples.basicTestRepoList());
        mockMvc.perform(get("/api/attendanceMarks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(18));

        //findByCourseId
        List<AttendanceMark> marksFor220 = AttendanceMarkRespositoryExamples.basicTestRepoList().stream().filter(mark-> mark.getCourseId().equals( "COMP220")).collect(Collectors.toList());
        when(attendanceMarkRepository.findByCourseId("COMP220")).thenReturn(marksFor220);
        mockMvc.perform(get("/api/attendanceMarks?courseId=COMP220"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(10));
        
        //findByStudentId
        List<AttendanceMark> marksForKatie = AttendanceMarkRespositoryExamples.basicTestRepoList().stream().filter(mark-> mark.getStudentId().equals( "Katie")).collect(Collectors.toList());
        when(attendanceMarkRepository.findByStudentId("Katie")).thenReturn(marksForKatie);
        mockMvc.perform(get("/api/attendanceMarks?studentId=Katie"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(4));

        //findByBoth
        mockMvc.perform(get("/api/attendanceMarks?studentId=Katie&courseId=COMP220"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testRecordAttendance() {

    }
}
