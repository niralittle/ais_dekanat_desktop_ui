package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Department {
    private IntegerProperty departmentId;
    private StringProperty name;
    private StringProperty mainInfo;

    public Department() {
    }

    public Department(Integer departmentId, String name, String mainInfo) {
        this.departmentId =new SimpleIntegerProperty(departmentId);
        this.name =new SimpleStringProperty(name);
        this.mainInfo =new SimpleStringProperty(mainInfo);
    }

    public Integer getDepartmentId() {
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

    public String getMainInfo() {
        return mainInfo.get();
    }

    public StringProperty mainInfoProperty() {
        return mainInfo;
    }

    public void setMainInfo(String mainInfo) {
        this.mainInfo.set(mainInfo);
    }
}
