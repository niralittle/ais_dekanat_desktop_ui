package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class Exam {
    private IntegerProperty examId;
    private Room room;
    private UniversityGroup group;
    private Subject subject;
    private LocalDate date;

    public Exam() {
        examId = new SimpleIntegerProperty();
    }

    public void setExamId(int examId) {
        this.examId.set(examId);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public UniversityGroup getGroup() {
        return group;
    }

    public void setGroup(UniversityGroup group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getExamId() {
        return examId.get();
    }

    public IntegerProperty examIdProperty() {
        return examId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
