package com.kma.ais_dekanat_desktop_ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class Subject {
    private IntegerProperty subjectId;
    private FinalType finalType;
    private StringProperty name;

    public Subject(Integer subjectId, FinalType finalType, String name){
        this.subjectId = new SimpleIntegerProperty(subjectId);
        this.finalType = finalType;
        this.name = new SimpleStringProperty(name);
    }

    public int getSubjectId() {
        return subjectId.get();
    }

    public IntegerProperty subjectIdProperty() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId.set(subjectId);
    }

    public FinalType getFinalType() {
        return finalType;
    }

    public void setFinalType(FinalType finalType) {
        this.finalType = finalType;
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
