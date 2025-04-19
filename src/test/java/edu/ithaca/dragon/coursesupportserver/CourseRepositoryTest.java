package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") // Activate the "test" profile for H2

public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @BeforeEach
    public void clearDatabase() {
        courseRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testSave() {
        assertTrue(courseRepository.findAll().isEmpty());
        courseRepository.save(new Course("COMP220", "Data Structures"));
        List<Course> courses = courseRepository.findAll();
        assertEquals(1, courses.size());
    }

    @Test
    public void testFindByCourseId() {
        courseRepository.save(new Course("COMP220", "Data Structures"));
        courseRepository.save(new Course("COMP172", "Intro to Programming"));
        assertEquals(1,
                courseRepository.findAll().stream().filter(course -> course.getCourseId().equals("COMP220")).count());
        assertEquals(1,
                courseRepository.findAll().stream().filter(course -> course.getCourseId().equals("COMP172")).count());
    }
}