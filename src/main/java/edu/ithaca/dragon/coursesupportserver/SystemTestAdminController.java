package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/reset-database")
    public String resetDatabase() {
        // Clear existing data
        // attendanceMarkRepository.deleteAll();
        // studentRepository.deleteAll();
        // groupRepository.deleteAll();
        // courseRepository.deleteAll();

        // Insert students
        Student student1 = new Student("john123", "John Doe");
        Student student2 = new Student("jane456", "Jane Smith");
        Student student3 = new Student("alex789", "Alex Johnson");
        Student student4 = new Student("emily321", "Emily Davis");
        studentRepository.saveAll(List.of(student1, student2, student3, student4));

        // Insert attendance marks
        AttendanceMark attendanceMark1 = new AttendanceMark("john123", "COMP220", 1, "present");
        AttendanceMark attendanceMark2 = new AttendanceMark("jane456", "COMP220", 1, "absent");
        AttendanceMark attendanceMark3 = new AttendanceMark("alex789", "COMP220", 1, "present");
        AttendanceMark attendanceMark4 = new AttendanceMark("emily321", "COMP220", 1, "present");
        AttendanceMark attendanceMark5 = new AttendanceMark("john123", "COMP220", 2, "absent");
        AttendanceMark attendanceMark6 = new AttendanceMark("jane456", "COMP220", 2, "present");
        attendanceMarkRepository.saveAll(List.of(attendanceMark1, attendanceMark2, attendanceMark3, attendanceMark4,
                attendanceMark5, attendanceMark6));

        // Insert groups
        Group group1 = new Group("2023-10-01", "[[ally, kate, brendan]; [sasha, connie, eren]; [rick, omar, kevin]]");
        Group group2 = new Group("2023-10-02", "[[john, jane]; [alex, emily]]");
        groupRepository.saveAll(List.of(group1, group2));

        // Insert courses
        Course course1 = new Course("COMP220", "Data Structures");
        Course course2 = new Course("COMP310", "Operating Systems");
        courseRepository.saveAll(List.of(course1, course2));

        return "System test database reset successfully!";
    }
}