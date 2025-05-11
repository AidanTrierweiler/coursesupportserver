package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class SystemTestAdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceMarkRepository attendanceMarkRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentResponseCounterRepository responseCounterRepository;

    @PostMapping("/reset-database")
    public String resetDatabase() {
        // Clear existing data
        attendanceMarkRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        responseCounterRepository.deleteAll();
        groupRepository.deleteAll(); // Clear groups as well

        // Insert courses
        Course course1 = new Course("COMP220", "Data Structures");
        Course course2 = new Course("COMP310", "Operating Systems");
        courseRepository.saveAll(List.of(course1, course2));

        // Insert students and associate them with courses
        Student student1 = new Student("john123", "John Doe");
        Student student2 = new Student("jane456", "Jane Smith");
        Student student3 = new Student("alex789", "Alex Johnson");
        Student student4 = new Student("emily321", "Emily Davis");

        student1.setCourses(Set.of(course1, course2)); // John is in both courses
        student2.setCourses(Set.of(course1)); // Jane is only in COMP220
        student3.setCourses(Set.of(course2)); // Alex is only in COMP310
        student4.setCourses(Set.of(course2)); // Emily is only in COMP310

        studentRepository.saveAll(List.of(student1, student2, student3, student4));

        // Debug: Print saved students and their courses
        System.out.println("Saved students: " + studentRepository.findAll());

        // Insert attendance marks
        AttendanceMark attendanceMark1 = new AttendanceMark("john123", "COMP220", 1, "present");
        AttendanceMark attendanceMark2 = new AttendanceMark("jane456", "COMP220", 1, "absent");
        AttendanceMark attendanceMark3 = new AttendanceMark("alex789", "COMP310", 1, "present");
        AttendanceMark attendanceMark4 = new AttendanceMark("emily321", "COMP310", 1, "present");
        attendanceMarkRepository.saveAll(List.of(attendanceMark1, attendanceMark2, attendanceMark3, attendanceMark4));

        // Insert student response counters
        StudentResponseCounter counter1 = new StudentResponseCounter(student1.getId(), 0, 0);
        StudentResponseCounter counter2 = new StudentResponseCounter(student2.getId(), 0, 0);
        StudentResponseCounter counter3 = new StudentResponseCounter(student3.getId(), 0, 0);
        StudentResponseCounter counter4 = new StudentResponseCounter(student4.getId(), 0, 0);
        responseCounterRepository.saveAll(List.of(counter1, counter2, counter3, counter4));

        // Debug: Print saved response counters
        System.out.println("Saved response counters: " + responseCounterRepository.findAll());

        // Insert groups
        Group group1 = new Group("Group A", "[[john123, jane456]; [alex789, emily321]]");
        Group group2 = new Group("Group B", "[[john123, alex789]; [jane456, emily321]]");
        groupRepository.saveAll(List.of(group1, group2));

        // Debug: Print saved groups
        System.out.println("Saved groups: " + groupRepository.findAll());

        return "System test database reset successfully!";
    }
}