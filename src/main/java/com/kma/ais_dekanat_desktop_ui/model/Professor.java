package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Anna on 21.02.2016.
 */
public class Professor {
    private IntegerProperty professorId = new SimpleIntegerProperty();
    private StringProperty professorName = new SimpleStringProperty("");
    private StringProperty professorDegree= new SimpleStringProperty("");
    private IntegerProperty cathedraId = new SimpleIntegerProperty();

    public Professor(){

    }

    public Professor(Integer professorId, String professorName, String professorDegree, Integer cathedraId) {
        this.professorId = new SimpleIntegerProperty(professorId);
        this.professorName = new SimpleStringProperty(professorName);
        this.professorDegree = new SimpleStringProperty(professorDegree);
        this.cathedraId = new SimpleIntegerProperty(cathedraId);
    }

    public int getProfessorId() {
        return professorId.get();
    }


    public IntegerProperty professorIdProperty() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId.set(professorId);
    }

    public String getProfessorName() {
        return professorName.get();
    }

    public StringProperty professorNameProperty() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName.set(professorName);
    }

    public String getProfessorDegree() {
        return professorDegree.get();
    }

    public StringProperty professorDegreeProperty() {
        return professorDegree;
    }

    public void setProfessorDegree(String professorDegree) {
        this.professorDegree.set(professorDegree);
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

    @Override
    public String toString() {
        return "Professor{" +
                "professorId=" + professorId +
                ", professorName=" + professorName +
                ", professorDegree=" + professorDegree +
                ", cathedraId=" + cathedraId +
                '}';
    }
}
