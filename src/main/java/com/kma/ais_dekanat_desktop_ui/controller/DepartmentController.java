package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

/**
 * Created by denysburlakov on 20.02.16.
 */
public class DepartmentController {
    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TableColumn<Department, Integer> idColumn;

    @FXML
    private TableColumn<Department, String> nameColumn;

    @FXML
    private Button newDepartment;

    @FXML
    private Button editDepartment;

    @FXML
    private Button deleteDepartment;

    @FXML
    private Label nameLabel;

    @FXML
    private TextArea mainInfo;

    private DekanatRunner dekanatRunner;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        departmentTable.setRowFactory(tv -> {
            TableRow<Department> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Department clickedRow = row.getItem();
                    nameLabel.setText(clickedRow.getName());
                    mainInfo.setText(clickedRow.getMainInfo());
                }
            });
            return row;
        });
    }

    private void showDepartmentDetails(Department department) {
        departmentTable.getSelectionModel().select(department);
        nameLabel.setText(department.getName());
        mainInfo.setText(department.getMainInfo());
    }

    @FXML
    private void handleNewDepartment() {
        Department tempDepartment = new Department();
        boolean okClicked = dekanatRunner.showDepartmentEditDialog(tempDepartment);
        if (okClicked) {
            dekanatRunner.getDepartmentData().add(tempDepartment);
            refreshDepartmentTable();
            showDepartmentDetails(tempDepartment);
        }
    }

    @FXML
    private void handleEditDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            boolean okClicked = dekanatRunner.showDepartmentEditDialog(selectedDepartment);
            if (okClicked) {
                refreshDepartmentTable();
                showDepartmentDetails(selectedDepartment);
            }

        } else {
            // Nothing selected
          //  Alert alert = new Alert(Alert.AlertType.ERROR);
           // alert.setHeaderText("Select department");
           // alert.setContentText("Please, select department to edit");
        }
    }

    private void refreshDepartmentTable() {
        departmentTable.getColumns().get(0).setVisible(false);
        departmentTable.getColumns().get(0).setVisible(true);
    }

    public void setMainApp(DekanatRunner dekanatRunner) {
        this.dekanatRunner = dekanatRunner;
        // Add observable list data to the table
        departmentTable.setItems(dekanatRunner.getDepartmentData());
    }
}
