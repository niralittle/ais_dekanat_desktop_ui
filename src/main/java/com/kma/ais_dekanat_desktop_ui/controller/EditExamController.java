package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by denysburlakov on 04.04.16.
 */
public class EditExamController {


    @FXML
    private ComboBox<Subject> subjectName;

    @FXML
    private ComboBox<UniversityGroup> groupName;

    @FXML
    private ComboBox<Room> roomName;

    @FXML
    private DatePicker date;

    private Stage dialogStage;
    private Exam exam;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the exam to be edited in the dialog.
     *
     * @param exam
     */
    public void setExam(Exam exam) {
        this.exam = exam;
        subjectName.setItems(DekanatRunner.getInstance().getSubjectData());
        subjectName.getSelectionModel().select(exam.getSubject());
        groupName.setItems(DekanatRunner.getInstance().getGroupData());
        groupName.getSelectionModel().select(exam.getGroup());
        roomName.setItems(DekanatRunner.getInstance().getRoomData());
        roomName.getSelectionModel().select(exam.getRoom());
        date.setValue(exam.getDate());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            exam.setSubject(subjectName.getValue());
            exam.setGroup(groupName.getValue());
            exam.setRoom(roomName.getValue());
            exam.setDate(date.getValue());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Fields");
            alert.setContentText(errorMessage);
            return false;
        }
    }
}
