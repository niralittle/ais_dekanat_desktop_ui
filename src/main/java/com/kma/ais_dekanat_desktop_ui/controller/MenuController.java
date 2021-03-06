package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by denysburlakov on 22.02.16.
 */
public class MenuController {
    @FXML
    public void close(){
        System.exit(0);
    }

    @FXML
    public void openCathedras(){
        DekanatRunner.getInstance().showCathedraList();
    }

    @FXML
    public void openDepartments(){
        DekanatRunner.getInstance().loadDepartmentStage();
    }

    @FXML
    public void openStudents() {
        DekanatRunner.getInstance().showStudentList();
    }

    @FXML
    public void openAddClass() {
        DekanatRunner.getInstance().showAddClassForm();
    }

    @FXML
    public void openExams() {
        DekanatRunner.getInstance().loadExamStage();
    }
    @FXML
    public void openExamsByCourse() {
        DekanatRunner.getInstance().loadExamByCourseStage();
    }

    public void openExamsByProfessor(ActionEvent actionEvent) {
        DekanatRunner.getInstance().loadExamByProfessorStage();
    }
}
