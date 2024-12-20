package com.example.ejercicioe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EjercicioEApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EjercicioEApp.class.getResource("EjercicioE.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        Image icon = new Image(getClass().getResourceAsStream("/iconos/cuaderno.png"));
        stage.getIcons().add(icon);
        stage.setMaxWidth(840);
        stage.setMinWidth(565);
        stage.setMinHeight(325);
        stage.setTitle("Ejercicio E!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}