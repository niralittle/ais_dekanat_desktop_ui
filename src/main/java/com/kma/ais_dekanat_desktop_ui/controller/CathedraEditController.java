package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Anna on 22.02.2016.
 */
public class CathedraEditController {
    @FXML
    private TextField cathedraName;
    private Cathedra cathedra;

    public Cathedra getCathedra() {
        return cathedra;
    }

    public void setCathedra(Cathedra cathedra) {
        if (cathedra==null)return;
        this.cathedra=cathedra;
        cathedraName.setText(cathedra.getName());
    }
    public void actionClose(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage)sourse.getScene().getWindow();
        stage.hide();
    }
    public void actionSave(ActionEvent actionEvent){
        cathedra.setName(cathedraName.getText());
        actionClose(actionEvent);
    }
}
