package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anna on 21.02.2016.
 */

public class ProfessorEditController {
    @FXML
    private TextField professorId;
    @FXML
    private TextField professorName;
    @FXML
    private TextField professorDegree;
    @FXML
    private TextField cathedraId;
    @FXML
    private Button btn;

    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor){
        if(professor==null)  return;

        this.professor=professor;
        //professorId.setText(String.valueOf(professor.getProfessorId()));
        professorName.setText(professor.getProfessorName());
        professorDegree.setText(professor.getProfessorDegree());
        //cathedraId.setText(String.valueOf(professor.getCathedraId()));
    }
    public void actionClose(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage)sourse.getScene().getWindow();
        stage.hide();
    }
    public void actionSave(ActionEvent actionEvent){
        //professor.setProfessorId(Integer.parseInt(professorId.getText()));
        professor.setProfessorName(professorName.getText());
        professor.setProfessorDegree(professorDegree.getText());
       // professor.setCathedraId(Integer.parseInt(cathedraId.getText()));
        actionClose(actionEvent);
    }
}
