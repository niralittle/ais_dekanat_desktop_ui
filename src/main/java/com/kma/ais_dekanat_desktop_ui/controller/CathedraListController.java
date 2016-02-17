package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.util.StringConverter;

public class CathedraListController {
    @FXML
    private TableView<Cathedra> cathedraTable;
    @FXML
    private TableColumn<Cathedra, Integer> idColumn;
    @FXML
    private TableColumn<Cathedra, String> nameColumn;
    @FXML
    private ComboBox<Department> departmentComboBox;
    @FXML
    private Label NameLabel;
    @FXML
    private Label ProfessorLabel;

    private DekanatRunner dekanatRunner;

    public CathedraListController() {
    }

    @FXML
    private void initialize() {
        // Initialize the cathedra table with the two columns.
        comboBoxManage();
        cathedraTable.setRowFactory(tv -> {
            TableRow<Cathedra> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Cathedra clickedRow = row.getItem();
                    setLabels(clickedRow);
                }
            });
            return row;
        });
    }

    private void comboBoxManage() {
        departmentComboBox.setCellFactory((comboBox) -> {
            return new ListCell<Department>() {
                @Override
                protected void updateItem(Department item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });
        departmentComboBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {
                if (department == null) {
                    return null;
                } else {
                    return department.getName();
                }
            }

            @Override
            public Department fromString(String string) {
                return null;
            }


        });
        departmentComboBox.setOnAction((event) -> {
            Department selectedDepartment = departmentComboBox.getSelectionModel().getSelectedItem();
            System.out.println("ComboBox Action (selected: " + selectedDepartment.getName() + ")");

            if (selectedDepartment.getName().equals("Факультет Інформатики")) {
                cathedraTable.getColumns().get(0).setVisible(false);
                cathedraTable.getColumns().get(0).setVisible(true);

                idColumn.setCellValueFactory(cellData -> cellData.getValue().cathedraIdProperty().asObject());
                nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            } else {
                cathedraTable.getColumns().get(0).setVisible(false);
                cathedraTable.getColumns().get(0).setVisible(true);

                idColumn.setCellValueFactory(cellData -> null);
                nameColumn.setCellValueFactory(cellData -> null);
            }

        });


    }

    public void setLabels(Cathedra cathedra) {
        NameLabel.setText(cathedra.getName());
        ProfessorLabel.setText("" + cathedra.getCathedraId());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(DekanatRunner dekanatRunner) {
        this.dekanatRunner = dekanatRunner;

        // Add observable list data to the table
        cathedraTable.setItems(dekanatRunner.getCathedraData());
        departmentComboBox.setItems(dekanatRunner.getDekanatData());
    }
}
