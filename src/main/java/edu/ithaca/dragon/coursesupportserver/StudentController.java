package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create a new student
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED); // Explicitly return 201 Created
    }

    // Get all students or filter by courseId
    @GetMapping
    public ResponseEntity<?> getAllStudents(@RequestParam(required = false) String netpass,
            @RequestParam(required = false) String courseId) {
        if (netpass != null) {
            List<Student> students = studentRepository.findByNetpass(netpass)
                    .map(List::of)
                    .orElse(List.of());
            return ResponseEntity.ok(students);
        }
        if (courseId != null) {
            List<Student> students = studentRepository.findByCourseId(courseId);
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.ok(studentRepository.findAll());
    }

    // Get a single student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
