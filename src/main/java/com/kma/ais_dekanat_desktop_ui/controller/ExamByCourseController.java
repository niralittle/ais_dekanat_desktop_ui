package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Exam;
import com.kma.ais_dekanat_desktop_ui.model.UniversityGroup;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

import java.time.LocalDate;

public class ExamByCourseController {
    @FXML
    private TableView<Exam> examTable;
    @FXML
    private TableColumn<Exam, String> subjectColumn;
    @FXML
    private TableColumn<Exam, String> roomColumn;
    @FXML
    private TableColumn<Exam, LocalDate> dateColumn;

    @FXML
    private ComboBox<UniversityGroup> courseComboBox;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private DekanatRunner dekanatRunner;

    public ExamByCourseController() {
    }

    @FXML
    private void initialize() {
      //  courseComboBox.setItems(FXCollections.observableArrayList(CathedraService.getAllDepartment()));
        comboBoxManage();
    }


    private void comboBoxManage() {
        courseComboBox.setCellFactory((comboBox) -> {
            return new ListCell<UniversityGroup>() {
                @Override
                protected void updateItem(UniversityGroup item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });
        courseComboBox.setConverter(new StringConverter<UniversityGroup>() {
            @Override
            public String toString(UniversityGroup universityGroup) {
                if (universityGroup == null) {
                    return null;
                } else {
                    return universityGroup.getName();
                }
            }

            @Override
            public UniversityGroup fromString(String string) {
                return null;
            }


        });
        courseComboBox.setOnAction((event) -> {
            UniversityGroup selectedDepartment = courseComboBox.getSelectionModel().getSelectedItem();
            System.out.println("ComboBox Action (selected: " + selectedDepartment.getGroupId() + ")");

            if (selectedDepartment.getGroupId()== 1) {


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
        this.dekanatRunner = dekanatRunner;
        // Add observable list data to the table
        courseComboBox.setItems(dekanatRunner.getGroupData());
        examTable.setItems(dekanatRunner.getExamData());
    }
}
