package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED); // Explicitly return 201 Created
    }

    @GetMapping
    public List<Student> getAllStudents(@RequestParam(required = false) String netpass) {
        if (netpass != null) {
            return studentRepository.findByNetpass(netpass); // Filter by netpass if provided
        }
        return studentRepository.findAll(); // Return all students if no filter is provided
    }
}
