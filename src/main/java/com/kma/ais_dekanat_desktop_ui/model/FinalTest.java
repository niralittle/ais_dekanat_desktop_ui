package com.kma.ais_dekanat_desktop_ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import javafx.beans.property.IntegerProperty;

import java.beans.Transient;
import java.sql.Date;
import java.util.Set;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class FinalTest {
    private Integer finalId;
    private UniversityGroup group;
    private Subject subject;
    private Date time;
    private Room room;
    private Set<FinalReport> finalReports;
    @JsonIgnoreProperties
    private IntegerProperty examId;

    public void setExamId(int examId) {
        this.examId.set(examId);
    }

    public void getExamId() {
        this.examId.get();
    }

    public IntegerProperty examIdProperty() {
        return examId;
    }

    public Integer getFinalId() {
        return finalId;
    }

    public void setFinalId(Integer finalId) {
        this.finalId = finalId;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<FinalReport> getFinalReports() {
        return finalReports;
    }

    public void setFinalReports(Set<FinalReport> finalReports) {
        this.finalReports = finalReports;
    }
}
