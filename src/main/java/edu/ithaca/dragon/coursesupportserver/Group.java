package edu.ithaca.dragon.coursesupportserver;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "group_students", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "student_id")
    private List<String> studentIds;

    public Group() {
    }

    public Group(String name, List<String> studentIds) {
        this.name = name;
        this.studentIds = studentIds;
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

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }
}
