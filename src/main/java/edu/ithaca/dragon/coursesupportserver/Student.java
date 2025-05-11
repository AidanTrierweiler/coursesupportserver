package edu.ithaca.dragon.coursesupportserver;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "netpass", unique = true, nullable = false)
    private String netpass;

    @Column(name = "preferred_name", nullable = false)
    private String preferredName;

    @ManyToMany
    @JoinTable(name = "student_courses", // Name of the join table
            joinColumns = @JoinColumn(name = "student_id"), // Foreign key in the join table referencing Student
            inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key in the join table referencing Course
    )
    private Set<Course> courses;

    public Student() {
    }

    public Student(String netpass, String preferredName) {
        this.netpass = netpass;
        this.preferredName = preferredName;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNetpass() {
        return netpass;
    }

    public void setNetpass(String netpass) {
        this.netpass = netpass;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
