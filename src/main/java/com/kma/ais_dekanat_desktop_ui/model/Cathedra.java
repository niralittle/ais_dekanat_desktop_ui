package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cathedra {
    private IntegerProperty cathedraId;
    private StringProperty name;
    private Department department;
   // private Set<Professor> professors;


    public Cathedra() {
        //this(null, null);
    }

    public Cathedra(int cathedraId, String name) {
        this.cathedraId =new SimpleIntegerProperty(cathedraId);
        this.name = new SimpleStringProperty(name);
    }

    public int getCathedraId() {
        return cathedraId.get();
    }

    public IntegerProperty cathedraIdProperty() {
        return cathedraId;
    }

    public void setCathedraId(int cathedraId) {
        this.cathedraId.set(cathedraId);
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
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
