package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.dialog.Dialogs;

public class StudentController {
    @FXML
    private TableView<Student> studnetTableView;
    @FXML
    private TableColumn<Student, String> studentFirstNameColumn;
    @FXML
    private TableColumn<Student, String> studentLastNameColumn;
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private ComboBox<Department> departmentStudComboBox;
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label groupLabel;

    private DekanatRunner dekanatRunner;

    public StudentController() {
    }

    @FXML
    private void initialize() {
        // Initialize the student table with the two columns.
        studentFirstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        studentLastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        showPersonDetails(null);

        studnetTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

        setDepartmentComboBox();

        courseComboBox.setDisable(true);
        courseComboBox.setStyle("-fx-opacity: 1;");
    }

    @FXML
    private void handleDeleteStudent() {
        int selectedIndex = studnetTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studnetTableView.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    @FXML
    private void handleNewStudent() {
        Student student = new Student();
        boolean okClicked = dekanatRunner.showStudentEditDialog(student);
        if (okClicked) {
            dekanatRunner.getStudentData().add(student);
        }
    }

    private void showPersonDetails(Student student) {
        if (student != null) {
            studentNameLabel.setText(student.getFirstName() + " " + student.getLastName());
        } else {
            studentNameLabel.setText("");
        }
    }

    @FXML
    private void handleEditPerson() {
        Student selectedPerson = studnetTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = dekanatRunner.showStudentEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    private void setDepartmentComboBox() {
        departmentStudComboBox.setCellFactory((comboBox) -> {
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
        departmentStudComboBox.setConverter(new StringConverter<Department>() {
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
        departmentStudComboBox.setOnAction((event) -> {
            Department selectedDepartment = departmentStudComboBox.getSelectionModel().getSelectedItem();
            System.out.println("ComboBox Action (selected: " + selectedDepartment.getName() + ")");

            courseComboBox.setDisable(false);

            if (selectedDepartment.getName().equals("Факультет Інформатики")) {
                studnetTableView.getColumns().get(0).setVisible(false);
                studnetTableView.getColumns().get(0).setVisible(true);

                studentFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
                studentFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
            } else {
                studnetTableView.getColumns().get(0).setVisible(false);
                studnetTableView.getColumns().get(0).setVisible(true);

                studentFirstNameColumn.setCellValueFactory(cellData -> null);
                studentLastNameColumn.setCellValueFactory(cellData -> null);
            }

        });


    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(DekanatRunner dekanatRunner) {
        this.dekanatRunner = dekanatRunner;

        // Add observable list data to the table
        studnetTableView.setItems(dekanatRunner.getStudentData());
        departmentStudComboBox.setItems(dekanatRunner.getDekanatData());
        courseComboBox.setItems(dekanatRunner.getCourseData());
    }
}
