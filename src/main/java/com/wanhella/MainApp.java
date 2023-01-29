package com.wanhella;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("scene.fxml");
        System.out.println(url);
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Job Applications");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}