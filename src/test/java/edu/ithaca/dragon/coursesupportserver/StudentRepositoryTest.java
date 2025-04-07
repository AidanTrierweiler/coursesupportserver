package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") // Activate the "test" profile for H2
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void testSave() {
        assertTrue(studentRepository.findAll().isEmpty());
        studentRepository.save(new Student("katie123", "Katie"));
        List<Student> students = studentRepository.findAll();
        assertEquals(1, students.size());
    }

    @Test
    public void testFindByNetpass() {
        studentRepository.save(new Student("katie123", "Katie"));
        studentRepository.save(new Student("jose456", "Jose"));
        assertEquals(1, studentRepository.findAll().stream().filter(student -> student.getNetpass().equals("katie123"))
                .count());
        assertEquals(1,
                studentRepository.findAll().stream().filter(student -> student.getNetpass().equals("jose456")).count());
    }
}