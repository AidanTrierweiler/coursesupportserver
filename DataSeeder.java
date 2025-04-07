package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AttendanceMarkRepository attendanceMarkRepository;

    @PostConstruct
    public void seedDatabase() {
        // Seed students
        if (studentRepository.count() == 0) {
            studentRepository.saveAll(List.of(
                    new Student("katie123", "Katie"),
                    new Student("jose456", "Jose"),
                    new Student("belinda789", "Belinda")));
        }

        // Seed courses
        if (courseRepository.count() == 0) {
            courseRepository.saveAll(List.of(
                    new Course("COMP220", "Data Structures"),
                    new Course("COMP172", "Intro to Programming")));
        }

        // Seed attendance marks
        if (attendanceMarkRepository.count() == 0) {
            attendanceMarkRepository.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        }
    }
}