package com.kma.ais_dekanat_desktop_ui;

import com.kma.ais_dekanat_desktop_ui.controller.*;
import com.kma.ais_dekanat_desktop_ui.model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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

    private static User user;
    private static DekanatRunner instance;

    public static DekanatRunner getInstance() {
        return instance;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Автоматизована інформаційна система \"Деканат\"");

        initRootLayout();
        promptLoginForm();
    }

    public void postLogin(User authenticated) {
        user = authenticated;
        loadDepartmentStage();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = newLoader("rootLayout.fxml");
            rootLayout = loader.load();
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
            FXMLLoader loader = newLoader("cathedraList.fxml");
            rootLayout.setCenter(loader.load());
            CathedraListController controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void promptLoginForm() {
        try {
            FXMLLoader loader = newLoader("loginForm.fxml");
            Pane cathedraList = loader.load();
            rootLayout.setCenter(cathedraList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStudentList() {
        try {
            FXMLLoader loader = newLoader("studentList.fxml");
            AnchorPane studentList = loader.load();

            rootLayout.setCenter(studentList);

            StudentController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showStudentEditDialog(Student student) {
        try {
            FXMLLoader loader = newLoader("student_edit_dialog.fxml");
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StudentEditDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudent(student);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showDepartmentEditDialog(Department department) {
        try {
            FXMLLoader loader = newLoader("departmentEditDialog.fxml");
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit/Create department");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditDepartmentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDepartment(department);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadProfessorStage(String cathedraName){
        try {
            FXMLLoader loader = newLoader("professorLayout.fxml");
            VBox professorPane = loader.load();
            rootLayout.setCenter(professorPane);
            ProfessorsController controller = loader.getController();
            controller.cathedraName=cathedraName;
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDepartmentStage() {
        fillDepartmentData();
        try {
            FXMLLoader loader = newLoader("departmentLayout.fxml");
            AnchorPane departmentPane = loader.load();

            rootLayout.setCenter(departmentPane);

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
    public void fillDepartmentData(){
        departmentData = FXCollections.observableArrayList();
        departmentData.add(new Department(1, "Факультет Інформатики", "IT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITITIT IT IT IT ITv IT ITITITITITIT"));
        departmentData.add(new Department(2, "Факультет Гуманітарних наук", "УГ"));
    }

    public ObservableList<Department> getDepartmentData() {
        return departmentData;
    }

    public ObservableList<String> getCourseData() {
        ObservableList<String> courseData = FXCollections.observableArrayList();
        courseData.add("1");
        courseData.add("2");
        courseData.add("3");
        courseData.add("4");
        return courseData;
    }

    public void showAddClassForm() {
        try {
            FXMLLoader loader = newLoader("addClass.fxml");
            rootLayout.setCenter(loader.load());
            ClassesFormController controller = loader.getController();
            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader newLoader(String filename) {
        return new FXMLLoader(this.getClass().getResource("/view/" + filename));
    }

    public static void main(String[] args) {
        launch(args);
    }

}

