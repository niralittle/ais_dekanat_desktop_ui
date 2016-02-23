package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import javafx.event.Event;
import javafx.scene.control.*;

public class ClassesFormController {

    private static final String REST_API_PATH = "http://localhost:8081";

    public TextField classNameField;
    public ComboBox<Department> facultyBox;
    public ComboBox<String> yearBox;
    public ComboBox<Professor> professorBox;
    public ComboBox<String> groupBox;
    public RadioButton isLecture;
    public RadioButton isPractice;
    public Button add;

    public void addNewClass(Event event) {}

    public void init() {
        DekanatRunner.getInstance().fillDepartmentData();
        facultyBox.setItems(DekanatRunner.getInstance().getDepartmentData());
        yearBox.setItems(DekanatRunner.getInstance().getCourseData());
        groupBox.setItems(DekanatRunner.getInstance().getCourseData());
        professorBox.setItems(DekanatRunner.getInstance().getProfessorData());
    }
}
