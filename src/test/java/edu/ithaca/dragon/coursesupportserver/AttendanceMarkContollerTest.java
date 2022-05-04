package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.ithaca.dragon.coursesupportserver.reportmodel.AttendanceCourseReport;
import edu.ithaca.dragon.util.JsonUtil;

@WebMvcTest(AttendanceMarkController.class)
public class AttendanceMarkContollerTest {
    @MockBean
    private AttendanceMarkRepository attendanceMarkRepository;
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetAllAttendanceMarks() throws Exception {
        //findAll
        when(attendanceMarkRepository.findAll()).thenReturn(AttendanceMarkRespositoryExamples.basicTestRepoList());
        mockMvc.perform(get("/api/attendanceMarks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(54));

        //findByCourseId
        List<AttendanceMark> marksFor220 = AttendanceMarkRespositoryExamples.basicTestRepoList().stream().filter(mark-> mark.getCourseId().equals( "COMP220")).collect(Collectors.toList());
        when(attendanceMarkRepository.findByCourseId("COMP220")).thenReturn(marksFor220);
        mockMvc.perform(get("/api/attendanceMarks?courseId=COMP220"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(30));
        
        //findByStudentId
        List<AttendanceMark> marksForKatie = AttendanceMarkRespositoryExamples.basicTestRepoList().stream().filter(mark-> mark.getStudentId().equals( "Katie")).collect(Collectors.toList());
        when(attendanceMarkRepository.findByStudentId("Katie")).thenReturn(marksForKatie);
        mockMvc.perform(get("/api/attendanceMarks?studentId=Katie"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(12));

        //findByBoth
        mockMvc.perform(get("/api/attendanceMarks?studentId=Katie&courseId=COMP220"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(6));
    }

    @Test
    public void generateAttendanceReportUrlCallTest() throws Exception {
        List<AttendanceMark> marksFor220 = AttendanceMarkRespositoryExamples.basicTestRepoList().stream().filter(mark-> mark.getCourseId().equals( "COMP220")).collect(Collectors.toList());
        
        when(attendanceMarkRepository.findByCourseId("COMP220")).thenReturn(marksFor220);
        mockMvc.perform(get("/api/attendanceReport?courseId=COMP220"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.courseId").value("COMP220"))
            .andExpect(jsonPath("$.studentReports.size()").value(5))
            .andExpect(jsonPath("$.studentReports.[0].marks.size()").value(6))
            .andExpect(jsonPath("$.studentReports.[0].marks.size()").value(6))
            ;
    }

    @Test
    public void generateAttendanceReportTest() throws Exception {
        List<AttendanceMark> marksFor220 = AttendanceMarkRespositoryExamples.basicTestRepoList().stream().filter(mark-> mark.getCourseId().equals( "COMP220")).collect(Collectors.toList());
        AttendanceCourseReport report = AttendanceMarkController.generateAttendanceReport("COMP220", marksFor220);
        System.out.println(JsonUtil.toJsonString(report));
    }

}
