package com.kma.ais_dekanat_desktop_ui.controller;
import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;

/**
 * Created by Anna on 21.02.2016.
 */
public class ProfessorsController {
    public String cathedraName;
    @FXML
    private TableView<Professor> professorsTable;
    @FXML
    private TableColumn<Professor, Integer> idColumn;
    @FXML
    private TableColumn<Professor, String> nameColumn;
    @FXML
    private TableColumn<Professor, String> degreeColumn;
    @FXML
    private TableColumn<Professor, Integer> idCatherda;
    @FXML
    private Button newProfessor;
    @FXML
    private Button editProfessor;
    @FXML
    private Button deleteProfessor;
    @FXML
    private Label Label;


    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private ProfessorEditController editController;
    private Stage editDialogStage;
    private DekanatRunner dekanatRunner;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Professor, Integer>("professorId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Professor, String>("professorName"));
        degreeColumn.setCellValueFactory(new PropertyValueFactory<Professor, String>("professorDegree"));
        //idCatherda.setCellValueFactory(new PropertyValueFactory<Professor, Integer>("cathedraId"));
        initLoaders();
    }

    private void initLoaders() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/view/professorEdit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMainApp(DekanatRunner dekanatRunner) {
        this.dekanatRunner = dekanatRunner;
        professorsTable.setItems(dekanatRunner.getProfessorData());
        Label.setText(cathedraName);
    }
    public void actionButtonPressed(ActionEvent actionEvent ) {
       Object sourse = actionEvent.getSource();
        if(!(sourse instanceof Button)){
            return;
        }
        Button clixkedButton = (Button) sourse;
        Professor selectedProfessor =(Professor)professorsTable.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editController.setProfessor(selectedProfessor);
        switch (clixkedButton.getId()){
            case "newProfessor":
                editController.setProfessor(new Professor());
                showDialog(parentWindow);
                dekanatRunner.proffesorData.add(editController.getProfessor());
                break;
            case "editProfessor":
                editController.setProfessor((Professor)professorsTable.getSelectionModel().getSelectedItem());
                showDialog(parentWindow);
                break;
            case "deleteProfessor":
                dekanatRunner.proffesorData.remove((Professor)professorsTable.getSelectionModel().getSelectedItem());
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
    public void showCathedra(ActionEvent actionEvent){
        dekanatRunner.showCathedraList();
    }
}
