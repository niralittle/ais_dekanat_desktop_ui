package com.kma.ais_dekanat_desktop_ui.controller;

import com.kma.ais_dekanat_desktop_ui.DekanatRunner;
import com.kma.ais_dekanat_desktop_ui.model.User;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.web.client.RestTemplate;

public class LoginFormController {

    private static final String REST_API_PATH = "http://localhost:8081";
    private static final String AUTH_API_PATH = "/auth";
    public TextField loginField;
    public PasswordField passwordField;
    public Label errorLabel;


    public void authorize() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (nonEmpty(login, password)) {
            RestTemplate restTemplate = new RestTemplate();
            User user = restTemplate.getForObject(REST_API_PATH + AUTH_API_PATH +
                    "?username=" + login + "&password=" + password, User.class);
            if (user != null) {
                DekanatRunner.getInstance().postLogin(user);
            } else {
                errorLabel.setVisible(true);
            }
        }
    }

    private boolean nonEmpty(String...values) {
        for (String s : values) {
            if (s == null || s.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
