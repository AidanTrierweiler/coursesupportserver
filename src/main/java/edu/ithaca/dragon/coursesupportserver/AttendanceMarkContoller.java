package edu.ithaca.dragon.coursesupportserver;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AttendanceMarkContoller {
    @Autowired
    AttendanceMarkRepository attendanceMarkRepository;

    @GetMapping("/attendanceMarks")
    public ResponseEntity<List<AttendanceMark>> getAllAttendanceMarks(
        @RequestParam(required=false) String courseId, 
        @RequestParam(required=false) String studentId
    ){
        List<AttendanceMark> responses=null;
        if (studentId!=null){
            if (courseId != null){
                responses = attendanceMarkRepository.findByStudentId(studentId).stream().filter(response -> response.getCourseId().equals(courseId)).collect(Collectors.toList());
            }
            else {
                responses = attendanceMarkRepository.findByStudentId(studentId);
            }
        }
        else{
            if (courseId!=null){
                responses = attendanceMarkRepository.findByCourseId(courseId);
            }
            else {
                responses = attendanceMarkRepository.findAll();
            }
        }
        if (responses != null && responses.size() != 0){
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/attendanceMarks")
    public ResponseEntity<List<AttendanceMark>> recordAttendance(@RequestBody List<AttendanceMark> attendanceMarks){
        try {
            List<AttendanceMark> newDbResponse = attendanceMarkRepository.saveAll(attendanceMarks);
            return new ResponseEntity<>(newDbResponse, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
