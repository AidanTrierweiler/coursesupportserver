package edu.ithaca.dragon.coursesupportserver;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "netpass")
    private String netpass;

    @Column(name = "preferred_name")
    private String preferredName;

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
}
