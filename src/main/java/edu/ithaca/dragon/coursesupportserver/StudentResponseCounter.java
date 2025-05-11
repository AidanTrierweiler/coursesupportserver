package edu.ithaca.dragon.coursesupportserver;

import javax.persistence.*;

@Entity
@Table(name = "student_response_counters")
public class StudentResponseCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_id", nullable = false, unique = true)
    private long studentId;

    @Column(name = "answered_count", nullable = false)
    private int answeredCount;

    @Column(name = "passed_count", nullable = false)
    private int passedCount;

    public StudentResponseCounter() {
    }

    public StudentResponseCounter(long studentId, int answeredCount, int passedCount) {
        this.studentId = studentId;
        this.answeredCount = answeredCount;
        this.passedCount = passedCount;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getAnsweredCount() {
        return answeredCount;
    }

    public void setAnsweredCount(int answeredCount) {
        this.answeredCount = answeredCount;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }
}
