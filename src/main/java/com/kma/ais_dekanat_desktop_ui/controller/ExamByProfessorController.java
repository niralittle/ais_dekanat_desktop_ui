package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.FinalTest;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import com.kma.ais_dekanat_desktop_ui.rest.ProfessorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.time.LocalDate;

public class ExamByProfessorController {
    @FXML
    private TableView<FinalTest> examTable;
    @FXML
    private TableColumn<FinalTest, String> subjectColumn;
    @FXML
    private TableColumn<FinalTest, String> roomColumn;
    @FXML
    private TableColumn<FinalTest, LocalDate> dateColumn;

    @FXML
    private ComboBox<Professor> professorComboBox;

    ProfessorService professorService = new ProfessorService();

    public ExamByProfessorController() {
    }

    @FXML
    private void initialize() {
        professorComboBox.setItems(FXCollections.observableArrayList(professorService.getAll()));
        comboBoxManage();
    }


    private void comboBoxManage() {
        professorComboBox.setCellFactory((comboBox) -> new ListCell<Professor>() {
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
        professorComboBox.setConverter(new StringConverter<Professor>() {
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
                return null;
            }


        });
        professorComboBox.setOnAction((event) -> {
            Professor selectedProfessor = professorComboBox.getSelectionModel().getSelectedItem();
            System.out.println("ComboBox Action: selected professor with name " + selectedProfessor.getProfessorName());

            if (selectedProfessor.getProfessorId() == 1) {


                examTable.getColumns().get(0).setVisible(false);
                examTable.getColumns().get(0).setVisible(true);

                subjectColumn.setCellValueFactory(cellData -> cellData.getValue().getSubject().nameProperty());
                roomColumn.setCellValueFactory(cellData -> cellData.getValue().getRoom().nameProperty());
                // dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDate().toString());
            } else {
                examTable.getColumns().get(0).setVisible(false);
                examTable.getColumns().get(0).setVisible(true);
                subjectColumn.setCellValueFactory(cellData -> null);
                roomColumn.setCellValueFactory(cellData -> null);
            }
        });

    }


    public void setMainApp(DekanatRunner dekanatRunner) {
        // Add observable list data to the table
        professorComboBox.setItems(dekanatRunner.getProfessorData());
        examTable.setItems(dekanatRunner.getExamData());
    }
}


