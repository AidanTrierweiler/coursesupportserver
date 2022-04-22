package edu.ithaca.dragon.coursesupportserver.reportmodel;

import java.util.ArrayList;
import java.util.List;

public class AttendanceStudentReport{
    private String name;
    private List<AttendanceReportMark> marks;
    
    public AttendanceStudentReport() {
        marks = new ArrayList<>();
    }
    
    public AttendanceStudentReport(String name, List<AttendanceReportMark> marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttendanceReportMark> getMarks() {
        return marks;
    }

    public void setMarks(List<AttendanceReportMark> marks) {
        this.marks = marks;
    }

    
}