package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.FinalTest;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class ExamController {
    @FXML
    private TableView<FinalTest> examTable;

    @FXML
    private TableColumn<FinalTest, Integer> idColumn;

    @FXML
    private TableColumn<FinalTest, String> subjectNameColumn;

    @FXML
    private Button newExam;

    @FXML
    private Button editExam;

    @FXML
    private Button deleteExam;

    @FXML
    private Label subjectNameLabel;

    @FXML
    private Label groupNameLabel;

    @FXML
    private Label roomNameLabel;

    @FXML
    private Label dateLabel;

    private DekanatRunner dekanatRunner;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().examIdProperty().asObject());
        subjectNameColumn.setCellValueFactory(cellData -> cellData.getValue().getSubject().nameProperty());
        examTable.setRowFactory(tv -> {
            TableRow<FinalTest> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    FinalTest clickedRow = row.getItem();
                    subjectNameLabel.setText(clickedRow.getSubject().getName());
                    groupNameLabel.setText(clickedRow.getGroup().getName());
                    roomNameLabel.setText(clickedRow.getRoom().getName());
                    dateLabel.setText(clickedRow.getTime().toString());
                }
            });
            return row;
        });
    }

    private void showExamDetails(FinalTest exam) {
        examTable.getSelectionModel().select(exam);
        subjectNameLabel.setText(exam.getSubject().getName());
        groupNameLabel.setText(exam.getGroup().getName());
        roomNameLabel.setText(exam.getRoom().getName());
        dateLabel.setText(exam.getTime().toString());
    }

    @FXML
    private void handleNewExam() {
        FinalTest tempExam = new FinalTest();
        boolean okClicked = dekanatRunner.showAddExamDialog(tempExam);
        if (okClicked) {
            dekanatRunner.getExamData().add(tempExam);
            refreshDepartmentTable();
            showExamDetails(tempExam);
        }
    }

    @FXML
    private void handleEditExam() {
        FinalTest selectedExam = examTable.getSelectionModel().getSelectedItem();
        if (selectedExam != null) {
            boolean okClicked = dekanatRunner.showAddExamDialog(selectedExam);
            if (okClicked) {
                refreshDepartmentTable();
                showExamDetails(selectedExam);
            }

        } else {
            // Nothing selected
           // Alert alert = new Alert(Alert.AlertType.ERROR);
           // alert.setHeaderText("Select exam");
          //  alert.setContentText("Please, select exam to edit");
        }
    }

    private void refreshDepartmentTable() {
        examTable.getColumns().get(0).setVisible(false);
        examTable.getColumns().get(0).setVisible(true);
    }

    public void setMainApp(DekanatRunner dekanatRunner) {
        this.dekanatRunner = dekanatRunner;
        // Add observable list data to the table
        examTable.setItems(dekanatRunner.getExamData());
    }
}
