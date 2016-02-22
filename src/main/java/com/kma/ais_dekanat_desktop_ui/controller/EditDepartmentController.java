package com.kma.ais_dekanat_desktop_ui.controller;

/**
 * Created by denysburlakov on 22.02.16.
 */
import com.kma.ais_dekanat_desktop_ui.model.Department;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class EditDepartmentController {


    /**
     * Dialog to edit details of a person.
     *
     * @author Marco Jakob
     */

        @FXML
        private TextField nameField;
        @FXML
        private TextArea mainInfo;


        private Stage dialogStage;
        private Department department;
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
         * Sets the department to be edited in the dialog.
         *
         * @param department
         */
        public void setDepartment(Department department) {
            this.department = department;
            nameField.setText(department.getName());
            mainInfo.setText(department.getMainInfo());
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
                department.setMainInfo(mainInfo.getText());
                department.setName(nameField.getText());

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
               /* Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Fields");
                alert.setContentText(errorMessage);*/
                return false;
            }
        }
}
