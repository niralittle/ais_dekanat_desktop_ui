package com.kma.ais_dekanat_desktop_ui;

import com.kma.ais_dekanat_desktop_ui.controller.CathedraListController;
import com.kma.ais_dekanat_desktop_ui.model.Cathedra;
import com.kma.ais_dekanat_desktop_ui.model.Department;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DekanatRunner extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ais_dekanat_desktop_ui");

        initRootLayout();
        showCathedraList();
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCathedraList() {
        try {
            // Load cathedra view.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DekanatRunner.class.getResource("/view/cathedraList.fxml"));
            AnchorPane cathedraList = (AnchorPane) loader.load();

            // Set cathedra view into the center of root layout.
            rootLayout.setCenter(cathedraList);

            // Give the controller access to the dekanat app.
            CathedraListController controller = loader.getController();
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

    public ObservableList<Department> getDekanatData() {
        ObservableList<Department> cathedraData = FXCollections.observableArrayList();
        cathedraData.add(new Department(1, "Факультет Інформатики", "IT"));
        cathedraData.add(new Department(2, "Факультет Гуманітарних наук", "УГ"));
        return cathedraData;
    }
}

