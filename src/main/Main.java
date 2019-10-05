package main;

/*
 * Nombre del programa: Lector de archivos
 * Autor: Luis Ángel De Santiago Guerrero
 * Última fecha de modificación: 5 de Octubre de 2019
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PrincipalView.fxml"));
        stage.setTitle("Lectura de archivos");
        stage.setScene(new Scene(root, 600, 300));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
