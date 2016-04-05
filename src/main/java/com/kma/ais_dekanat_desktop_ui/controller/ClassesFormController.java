package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import com.kma.ais_dekanat_desktop_ui.model.UniversityGroup;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class ClassesFormController {

    private static final String REST_API_PATH = "http://localhost:8081";

    public TextField classNameField;
    public ComboBox<Department> facultyBox;
    public ComboBox<String> yearBox;
    public ComboBox<Professor> professorBox;
    public ComboBox<UniversityGroup> groupBox;
    public RadioButton isLecture;
    public RadioButton isPractice;
    public Button add;

    public void addNewClass(Event event) {}

    @FXML
    private void initialize() {
        facultyBox.setItems(DekanatRunner.getInstance().getDepartmentData());
        yearBox.setItems(DekanatRunner.getInstance().getCourseData());
        groupBox.setItems(DekanatRunner.getInstance().getGroupData());
        professorBox.setItems(DekanatRunner.getInstance().getProfessorData());
        comboBoxManage();
    }


    private void comboBoxManage() {
        facultyBox.setCellFactory((comboBox) -> new ListCell<Department>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        facultyBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department professor) {
                if (professor == null) {
                    return null;
                } else {
                    return professor.getName();
                }
            }

            @Override
            public Department fromString(String string) {
                return null;
            }
        });
        professorBox.setCellFactory((comboBox) -> new ListCell<Professor>() {
            @Override
            protected void updateItem(Professor item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getProfessorName());
                }
            }
        });
        professorBox.setConverter(new StringConverter<Professor>() {
            @Override
            public String toString(Professor professor) {
                if (professor == null) {
                    return null;
                } else {
                    return professor.getProfessorName();
                }
            }

            @Override
            public Professor fromString(String string) {
                Professor professor = new Professor();
                professor.setProfessorDegree(string);
                return professor;
            }
        });

        groupBox.setCellFactory((comboBox) -> new ListCell<UniversityGroup>() {
            @Override
            protected void updateItem(UniversityGroup item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        groupBox.setConverter(new StringConverter<UniversityGroup>() {
            @Override
            public String toString(UniversityGroup professor) {
                if (professor == null) {
                    return null;
                } else {
                    return professor.getName();
                }
            }

            @Override
            public UniversityGroup fromString(String string) {
                return null;
            }
        });
    }


}
