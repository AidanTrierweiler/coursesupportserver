package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

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
        groupRepository.deleteAll();

        // Insert courses
        Course course1 = new Course("COMP220", "Data Structures");
        Course course2 = new Course("COMP310", "Operating Systems");
        courseRepository.saveAll(List.of(course1, course2));

        // Insert students and associate them with courses
        Student student1 = new Student("john123", "John Doe");
        Student student2 = new Student("jane456", "Jane Smith");
        Student student3 = new Student("alex789", "Alex Johnson");
        Student student4 = new Student("emily321", "Emily Davis");
        Student student5 = new Student("mike111", "Mike Brown");
        Student student6 = new Student("sara222", "Sara White");
        Student student7 = new Student("lisa333", "Lisa Green");
        Student student8 = new Student("tom444", "Tom Black");
        Student student9 = new Student("nina555", "Nina Blue");
        Student student10 = new Student("paul666", "Paul Red");
        Student student11 = new Student("kate777", "Kate Yellow");
        Student student12 = new Student("omar888", "Omar Purple");
        Student student13 = new Student("zoe999", "Zoe Orange");
        Student student14 = new Student("ivan000", "Ivan Gray");

        student1.setCourses(new HashSet<>(List.of(course1, course2)));
        student2.setCourses(new HashSet<>(List.of(course1)));
        student3.setCourses(new HashSet<>(List.of(course2)));
        student4.setCourses(new HashSet<>(List.of(course2)));
        student5.setCourses(new HashSet<>(List.of(course1)));
        student6.setCourses(new HashSet<>(List.of(course1)));
        student7.setCourses(new HashSet<>(List.of(course2)));
        student8.setCourses(new HashSet<>(List.of(course2)));
        student9.setCourses(new HashSet<>(List.of(course1, course2)));
        student10.setCourses(new HashSet<>(List.of(course1)));
        student11.setCourses(new HashSet<>(List.of(course2)));
        student12.setCourses(new HashSet<>(List.of(course1)));
        student13.setCourses(new HashSet<>(List.of(course2)));
        student14.setCourses(new HashSet<>(List.of(course1, course2)));

        course1.setStudents(new HashSet<>(
                List.of(student1, student2, student5, student6, student9, student10, student12, student14)));
        course2.setStudents(new HashSet<>(
                List.of(student1, student3, student4, student7, student8, student9, student11, student13, student14)));

        studentRepository.saveAll(List.of(
                student1, student2, student3, student4, student5, student6, student7,
                student8, student9, student10, student11, student12, student13, student14));
        courseRepository.saveAll(List.of(course1, course2));

        // Debug: Print saved students and their courses
        System.out.println("Saved students: " + studentRepository.findAll());
        System.out.println("Saved courses: " + courseRepository.findAll());

        // Insert attendance marks
        AttendanceMark attendanceMark1 = new AttendanceMark("john123", "COMP220", 1, "present");
        AttendanceMark attendanceMark2 = new AttendanceMark("jane456", "COMP220", 1, "absent");
        AttendanceMark attendanceMark3 = new AttendanceMark("alex789", "COMP310", 1, "present");
        AttendanceMark attendanceMark4 = new AttendanceMark("emily321", "COMP310", 1, "present");
        attendanceMarkRepository.saveAll(List.of(attendanceMark1, attendanceMark2, attendanceMark3, attendanceMark4));

        // Debug: Print saved attendance marks
        System.out.println("Saved attendance marks: " + attendanceMarkRepository.findAll());

        // Insert student response counters
        StudentResponseCounter counter1 = new StudentResponseCounter(student1.getId(), 0, 0);
        StudentResponseCounter counter2 = new StudentResponseCounter(student2.getId(), 0, 0);
        StudentResponseCounter counter3 = new StudentResponseCounter(student3.getId(), 0, 0);
        StudentResponseCounter counter4 = new StudentResponseCounter(student4.getId(), 0, 0);
        responseCounterRepository.saveAll(List.of(counter1, counter2, counter3, counter4));

        // Debug: Print saved response counters
        System.out.println("Saved response counters: " + responseCounterRepository.findAll());

        // Insert groups with courseId
        Group group1 = new Group("Group A", "[[john123, jane456]; [alex789, emily321]]", course1.getCourseId());
        Group group2 = new Group("Group B", "[[john123, alex789]; [jane456, emily321]]", course2.getCourseId());
        Group group3 = new Group("Group C", "[[mike111, sara222]; [lisa333, tom444]]", course1.getCourseId());
        Group group4 = new Group("Group D", "[[nina555, paul666]; [kate777, omar888]]", course2.getCourseId());
        groupRepository.saveAll(List.of(group1, group2, group3, group4));

        // Debug: Print saved groups
        System.out.println("Saved groups: " + groupRepository.findAll());

        return "System test database reset successfully!";
    }
}