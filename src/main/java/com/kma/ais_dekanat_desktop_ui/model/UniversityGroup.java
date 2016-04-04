package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UniversityGroup {
    private IntegerProperty groupId;
    private Department department;
    private IntegerProperty course;
    private StringProperty name;

    public UniversityGroup(Integer groupId, Department department, Integer course, String name){
        this.groupId = new SimpleIntegerProperty(groupId);
        this.department = department;
        this.course = new SimpleIntegerProperty(course);
        this.name = new SimpleStringProperty(name);
    }

    public int getGroupId() {
        return groupId.get();
    }

    public IntegerProperty groupIdProperty() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId.set(groupId);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getCourse() {
        return course.get();
    }

    public IntegerProperty courseProperty() {
        return course;
    }

    public void setCourse(int course) {
        this.course.set(course);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String toString(){
        return getName();
    }
}
