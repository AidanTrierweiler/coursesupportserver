package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student-responses")
public class StudentResponseCounterController {

    @Autowired
    private StudentResponseCounterRepository responseCounterRepository;

    @PostMapping("/increment-answered/{studentId}")
    public ResponseEntity<StudentResponseCounter> incrementAnswered(@PathVariable long studentId) {
        Optional<StudentResponseCounter> optionalCounter = responseCounterRepository.findByStudentId(studentId);
        StudentResponseCounter counter = optionalCounter.orElseGet(() -> {
            StudentResponseCounter newCounter = new StudentResponseCounter(studentId, 0, 0);
            responseCounterRepository.save(newCounter);
            return newCounter;
        });
        counter.setAnsweredCount(counter.getAnsweredCount() + 1);
        responseCounterRepository.save(counter);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }

    @PostMapping("/increment-passed/{studentId}")
    public ResponseEntity<StudentResponseCounter> incrementPassed(@PathVariable long studentId) {
        Optional<StudentResponseCounter> optionalCounter = responseCounterRepository.findByStudentId(studentId);
        StudentResponseCounter counter = optionalCounter.orElse(new StudentResponseCounter(studentId, 0, 0));
        counter.setPassedCount(counter.getPassedCount() + 1);
        responseCounterRepository.save(counter);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseCounter> getCounter(@PathVariable long studentId) {
        Optional<StudentResponseCounter> optionalCounter = responseCounterRepository.findByStudentId(studentId);
        return optionalCounter.map(counter -> new ResponseEntity<>(counter, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}