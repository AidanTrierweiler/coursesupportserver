package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED); // Explicitly return 201 Created
    }

    @GetMapping
    public List<Course> getAllCourses(@RequestParam(required = false) String courseId) {
        if (courseId != null) {
            return courseRepository.findByCourseId(courseId); // Filter by courseId if provided
        }
        return courseRepository.findAll(); // Return all courses if no filter is provided
    }
}
