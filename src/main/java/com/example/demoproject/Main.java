package com.example.demoproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.Serializable;


public class Main<G> extends Application implements Serializable {
    boolean login = false;
    boolean createPost = false;
    Stage primaryStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override


    public void start(Stage primaryStage) {
        try {

            //ScrollPane root = (ScrollPane)FXMLLoader.load(getClass().getResource("TwittlrPage.fxml"));
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
            Scene scene1 = new Scene(root, 1910, 1010);
            //scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            this.primaryStage.setScene(scene1);
            this.primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
