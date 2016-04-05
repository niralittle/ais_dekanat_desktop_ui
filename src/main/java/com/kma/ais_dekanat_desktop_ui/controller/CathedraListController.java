package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.rest.CathedraService;
import com.kma.ais_dekanat_desktop_ui.rest.DepartmentService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.List;

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
    private Stage editDialogStage;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private CathedraEditController cathedraEditController;

    public CathedraListController() {
    }

    @FXML
    private void initialize() {
        departmentComboBox.setItems(FXCollections.observableArrayList(DepartmentService.getAll()));
        System.out.println("How many departments loaded: " + departmentComboBox.getItems().size());
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
        initLoaders();
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

            if (selectedDepartment != null) {
                List<Cathedra> cathedraList = CathedraService.getCathedraByDepartmentId(selectedDepartment.getDepartmentId());
                cathedraTable.setItems(FXCollections.observableArrayList(cathedraList));

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

    private void initLoaders() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/view/cathedraEdit.fxml"));
            fxmlEdit = fxmlLoader.load();
            cathedraEditController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLabels(Cathedra cathedra) {
        NameLabel.setText(cathedra.getName());

        ProfessorLabel.setText("" + cathedra.getCathedraId());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object sourse = actionEvent.getSource();
        if (!(sourse instanceof Button)) {
            return;
        }
        Button clixkedButton = (Button) sourse;
        Cathedra selectedCathedra = cathedraTable.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        cathedraEditController.setCathedra(selectedCathedra);
        switch (clixkedButton.getId()) {
            case "newCathedraBtn":
                cathedraEditController.setCathedra(new Cathedra());
                showDialog(parentWindow);
                DekanatRunner.getInstance().cathedraData.add(cathedraEditController.getCathedra());
                break;
            case "editCathedraBtn":
                cathedraEditController.setCathedra(cathedraTable.getSelectionModel().getSelectedItem());
                showDialog(parentWindow);
                break;
            case "deleteCathedraBtn":
                CathedraService.removeDepartment(cathedraTable.getSelectionModel().getSelectedItem().getCathedraId());
                DekanatRunner.getInstance().cathedraData.remove(cathedraTable.getSelectionModel().getSelectedItem());

                break;
        }
    }

    private void showDialog(Window window) {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Edit");
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(window);
        }
        editDialogStage.showAndWait();
    }


    public void showProfessors(ActionEvent actionEvent) {
        if (cathedraTable.getSelectionModel().getSelectedItem() != null) {
            DekanatRunner.getInstance().loadProfessorStage(cathedraTable.getSelectionModel().getSelectedItem().getName());
        }
    }
}