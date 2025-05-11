package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test") // Use H2 for testing
public class StudentResponseCounterRepositoryTest {

    @Autowired
    private StudentResponseCounterRepository responseCounterRepository;

    @BeforeEach
    public void clearDatabase() {
        responseCounterRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindByStudentId() {
        long studentId = 1L;
        StudentResponseCounter counter = new StudentResponseCounter(studentId, 3, 2);
        responseCounterRepository.save(counter);

        Optional<StudentResponseCounter> retrievedCounter = responseCounterRepository.findByStudentId(studentId);
        assertTrue(retrievedCounter.isPresent());
        assertEquals(3, retrievedCounter.get().getAnsweredCount());
        assertEquals(2, retrievedCounter.get().getPassedCount());
    }

    @Test
    public void testUpdateCounter() {
        long studentId = 1L;
        StudentResponseCounter counter = new StudentResponseCounter(studentId, 3, 2);
        responseCounterRepository.save(counter);

        // Update the counter
        counter.setAnsweredCount(5);
        counter.setPassedCount(4);
        responseCounterRepository.save(counter);

        Optional<StudentResponseCounter> updatedCounter = responseCounterRepository.findByStudentId(studentId);
        assertTrue(updatedCounter.isPresent());
        assertEquals(5, updatedCounter.get().getAnsweredCount());
        assertEquals(4, updatedCounter.get().getPassedCount());
    }
}