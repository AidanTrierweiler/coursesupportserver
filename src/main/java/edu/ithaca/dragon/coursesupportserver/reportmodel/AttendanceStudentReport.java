package edu.ithaca.dragon.coursesupportserver.reportmodel;

import java.util.ArrayList;
import java.util.List;

import edu.ithaca.dragon.coursesupportserver.AttendanceMark;

public class AttendanceStudentReport{
    private String name;
    private List<AttendanceReportMark> marks;
    
    public AttendanceStudentReport() {}

    public AttendanceStudentReport(AttendanceMark markToStartWith){
        this.name = markToStartWith.getStudentId();
        this.marks = new ArrayList<>();
        marks.add(new AttendanceReportMark(markToStartWith.getDayNumber(), markToStartWith.getStatus()));
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