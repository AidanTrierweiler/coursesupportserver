package edu.ithaca.dragon.coursesupportserver.reportmodel;

import java.util.Collection;
import java.util.List;

public class AttendanceCourseReport {
    private String courseId;
    private Collection<AttendanceStudentReport> studentReports;
    
    public AttendanceCourseReport() {}
    
    public AttendanceCourseReport(String courseId, Collection<AttendanceStudentReport> studentReports) {
        this.courseId = courseId;
        this.studentReports = studentReports;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Collection<AttendanceStudentReport> getStudentReports() {
        return studentReports;
    }

    public void setStudentReports(List<AttendanceStudentReport> studentReports) {
        this.studentReports = studentReports;
    }
}   
