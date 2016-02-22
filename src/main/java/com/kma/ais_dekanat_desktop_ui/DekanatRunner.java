package com.kma.ais_dekanat_desktop_ui;

import com.kma.ais_dekanat_desktop_ui.controller.*;
import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import com.kma.ais_dekanat_desktop_ui.model.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DekanatRunner extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    public ObservableList<Professor> proffesorData;
    public ObservableList<Cathedra> cathedraData;
    private ObservableList<Department> departmentData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ais_dekanat_desktop_ui");

        initRootLayout();
        showCathedraList();
        //showStudentList();
        //   loadDepartmentStage();
       // loadDepartmentStage();

    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            MenuController menuController = loader.getController();
            menuController.setMainApp(this);
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCathedraList() {
        fillDepartmentData();
        try {
            // Load cathedra com.kma.ais_dekanat_desktop_ui.view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/cathedraList.fxml"));
            AnchorPane cathedraList = (AnchorPane) loader.load();

            // Set cathedra com.kma.ais_dekanat_desktop_ui.view into the center of root layout.
            rootLayout.setCenter(cathedraList);

            // Give the controller access to the dekanat app.
            CathedraListController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStudentList() {
        try {
            // Load cathedra com.kma.ais_dekanat_desktop_ui.view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/studentList.fxml"));
            AnchorPane studentList = (AnchorPane) loader.load();

            // Set cathedra com.kma.ais_dekanat_desktop_ui.view into the center of root layout.
            rootLayout.setCenter(studentList);

            // Give the controller access to the dekanat app.
            StudentController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/student_edit_dialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the student into the controller.
            StudentEditDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showDepartmentEditDialog(Department department) {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader(DekanatRunner.class.getResource("/view/departmentEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit/Create department");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            EditDepartmentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDepartment(department);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
            return false;
        }
    }

    public void loadProfessorStage(String catherdaName){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/professorLayout.fxml"));
            VBox proofessorPane = loader.load();
            rootLayout.setCenter(proofessorPane);
            ProfessorsController controller = loader.getController();
            controller.cathedraName=catherdaName;
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadDepartmentStage() {
        fillDepartmentData();
        try {
            // Load cathedra view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/departmentLayout.fxml"));
            AnchorPane departmentPane = loader.load();

            // Set cathedra view into the center of root layout.
            rootLayout.setCenter(departmentPane);

            // Give the controller access to the dekanat app.
            DepartmentController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Cathedra> getCathedraData() {
        ObservableList<Cathedra> cathedraData = FXCollections.observableArrayList();
        cathedraData.add(new Cathedra(1, "Кафедра математики"));
        cathedraData.add(new Cathedra(2, "Кафедра мультимедійних систем"));
        return cathedraData;
    }

    public ObservableList<Student> getStudentData() {
        ObservableList<Student> studentData = FXCollections.observableArrayList();
        studentData.add(new Student("Hans", "Muster"));
        studentData.add(new Student("Ruth", "Mueller"));
        studentData.add(new Student("Heinz", "Kurz"));
        studentData.add(new Student("Cornelia", "Meier"));
        studentData.add(new Student("Werner", "Meyer"));
        studentData.add(new Student("Lydia", "Kunz"));
        studentData.add(new Student("Anna", "Best"));
        studentData.add(new Student("Stefan", "Meier"));
        studentData.add(new Student("Martin", "Mueller"));
        return studentData;
    }

    public ObservableList<Professor> getProfessorData() {
        proffesorData = FXCollections.observableArrayList();
        proffesorData.add(new Professor(1,"Jon","lector",1));
        proffesorData.add(new Professor(2, "Ivan", "lector",1));
        proffesorData.add(new Professor(2, "Maria", "teacher",1));
        return proffesorData;
    }
    private void fillDepartmentData(){
        departmentData = FXCollections.observableArrayList();
        departmentData.add(new Department(1, "Факультет Інформатики", "IT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITIT"));
        departmentData.add(new Department(2, "Факультет Гуманітарних наук", "УГ"));
    }

    public ObservableList<Department> getDepartmentData() {
        return departmentData;
    }

    public ObservableList<String> getCourseData() {
        ObservableList<String> courseData = FXCollections.observableArrayList();
        courseData.add(new String("1"));
        courseData.add(new String("2"));
        courseData.add(new String("3"));
        courseData.add(new String("4"));
        return courseData;
    }
}

