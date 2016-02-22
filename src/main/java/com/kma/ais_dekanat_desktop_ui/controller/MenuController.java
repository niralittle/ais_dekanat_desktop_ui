package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import javafx.fxml.FXML;

/**
 * Created by denysburlakov on 22.02.16.
 */
public class MenuController {
    DekanatRunner dekanatRunner;
    @FXML
    public void close(){
        System.exit(0);
    }

    @FXML
    public void openCathedras(){
        dekanatRunner.showCathedraList();
    }

    @FXML
    public void openDepartments(){
        dekanatRunner.loadDepartmentStage();
    }

    @FXML
    public void openStudents(){
        dekanatRunner.showStudentList();
    }

    public void setMainApp(DekanatRunner dekanatRunner){
        this.dekanatRunner = dekanatRunner;
    }
}
