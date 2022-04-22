package edu.ithaca.dragon.coursesupportserver.reportmodel;

public class AttendanceReportMark {
	private int dayNumber;
	private String status;
    
    public AttendanceReportMark() {}
    
    public AttendanceReportMark(int dayNumber, String status) {
        this.dayNumber = dayNumber;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getDayNumber() {
        return dayNumber;
    }
    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }
}