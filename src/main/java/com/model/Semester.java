package com.model;

import javax.persistence.*;

@Entity
@Table(name = "semester")
public class Semester {
    @Id
    @Column(name="semester_id")
    private String semesterId;

    @Column(name="is_current")
    private boolean isCurrent;

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
