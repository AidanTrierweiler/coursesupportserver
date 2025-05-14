package edu.ithaca.dragon.coursesupportserver;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "subgroups", columnDefinition = "TEXT") // Store subgroups as plain text
    private String subgroups;

    @Column(name = "course_id")
    private String courseId; // or Long courseId if your Course PK is Long

    public Group() {
    }

    public Group(String name, String subgroups, String courseId) {
        this.name = name;
        this.subgroups = subgroups;
        this.courseId = courseId;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(String subgroups) {
        this.subgroups = subgroups;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}