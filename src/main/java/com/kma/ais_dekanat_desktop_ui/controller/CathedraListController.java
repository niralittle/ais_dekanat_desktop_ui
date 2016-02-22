package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
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
        initLoaders();
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

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(DekanatRunner dekanatRunner) {
        this.dekanatRunner = dekanatRunner;

        // Add observable list data to the table
        cathedraTable.setItems(dekanatRunner.getCathedraData());
        departmentComboBox.setItems(dekanatRunner.getDekanatData());
    }
    public void actionButtonPressed(ActionEvent actionEvent ) {
        Object sourse = actionEvent.getSource();
        if(!(sourse instanceof Button)){
            return;
        }
        Button clixkedButton = (Button) sourse;
        Cathedra selectedCathedra=(Cathedra)cathedraTable.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        cathedraEditController.setCathedra(selectedCathedra);
        switch (clixkedButton.getId()){
            case "newCathedraBtn":
                cathedraEditController.setCathedra(new Cathedra());
                showDialog(parentWindow);
                dekanatRunner.cathedraData.add(cathedraEditController.getCathedra());
                break;
            case "editCathedraBtn":
                cathedraEditController.setCathedra((Cathedra)cathedraTable.getSelectionModel().getSelectedItem());
                showDialog(parentWindow);
                break;
            case "deleteCathedraBtn":
                dekanatRunner.cathedraData.remove((Cathedra)cathedraTable.getSelectionModel().getSelectedItem());
                break;
        }
    }
    private void showDialog(Window window){
        if(editDialogStage==null){
            editDialogStage=new Stage();
            editDialogStage.setTitle("Edit");
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(window);
        }
        editDialogStage.showAndWait();
    }


    public void showProfessors(ActionEvent actionEvent ) {
        Cathedra selectedCathedra = (Cathedra) cathedraTable.getSelectionModel().getSelectedItem();
        dekanatRunner.loadProfessorStage(selectedCathedra.getName().toString());
    }
}
