package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(StudentResponseCounterController.class)
public class StudentResponseCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentResponseCounterRepository responseCounterRepository;

    @Test
    public void testIncrementAnswered() throws Exception {
        long studentId = 1L;
        StudentResponseCounter counter = new StudentResponseCounter(studentId, 2, 1);

        when(responseCounterRepository.findByStudentId(studentId)).thenReturn(Optional.of(counter));

        mockMvc.perform(post("/api/student-responses/increment-answered/" + studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answeredCount").value(3)) // Incremented by 1
                .andExpect(jsonPath("$.passedCount").value(1)); // Unchanged

        verify(responseCounterRepository).save(counter);
    }

    @Test
    public void testIncrementPassed() throws Exception {
        long studentId = 1L;
        StudentResponseCounter counter = new StudentResponseCounter(studentId, 2, 1);

        when(responseCounterRepository.findByStudentId(studentId)).thenReturn(Optional.of(counter));

        mockMvc.perform(post("/api/student-responses/increment-passed/" + studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answeredCount").value(2)) // Unchanged
                .andExpect(jsonPath("$.passedCount").value(2)); // Incremented by 1

        verify(responseCounterRepository).save(counter);
    }

    @Test
    public void testGetCounter() throws Exception {
        long studentId = 1L;
        StudentResponseCounter counter = new StudentResponseCounter(studentId, 5, 3);

        when(responseCounterRepository.findByStudentId(studentId)).thenReturn(Optional.of(counter));

        mockMvc.perform(get("/api/student-responses/" + studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answeredCount").value(5))
                .andExpect(jsonPath("$.passedCount").value(3));
    }
}