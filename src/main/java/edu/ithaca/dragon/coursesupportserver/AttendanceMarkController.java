package edu.ithaca.dragon.coursesupportserver;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

import edu.ithaca.dragon.coursesupportserver.reportmodel.AttendanceCourseReport;
import edu.ithaca.dragon.coursesupportserver.reportmodel.AttendanceReportMark;
import edu.ithaca.dragon.coursesupportserver.reportmodel.AttendanceStudentReport;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AttendanceMarkController {
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

    @GetMapping("/attendanceReport")
    public ResponseEntity<AttendanceCourseReport> generateAttendanceReport( @RequestParam String courseId){
        Map<String, AttendanceStudentReport> student2marks = new TreeMap<>();
        List<AttendanceMark> marks = attendanceMarkRepository.findByCourseId(courseId);
        for (AttendanceMark mark : marks){
            AttendanceStudentReport previousMarks = student2marks.get(mark.getStudentId());
            if (previousMarks != null){
                previousMarks.getMarks().add(new AttendanceReportMark(mark.getDayNumber(), mark.getStatus()));
            }
            else {
                AttendanceStudentReport newStudentMarks = new AttendanceStudentReport(mark);
                student2marks.put(mark.getStudentId(), newStudentMarks);
            }
        }
        AttendanceCourseReport report = new AttendanceCourseReport(courseId, student2marks.values());
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
