package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class Room {
    private IntegerProperty departmentId;
    private StringProperty name;

    private Room(Integer departmentId, String name){
        this.departmentId = new SimpleIntegerProperty(departmentId);
        this.name = new SimpleStringProperty(name);
    }

    public int getDepartmentId() {
        return departmentId.get();
    }

    public IntegerProperty departmentIdProperty() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId.set(departmentId);
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
}
