package com.example.foorumfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VeaTeade extends Application {
    private String teade;

    public VeaTeade(String teade) {
        this.teade = teade;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Text text = new Text(teade);
        text.setY(50);
        text.setX(100);
        Group group = new Group();
        group.getChildren().add(text);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(group);
        Scene scene = new Scene(stackPane, 200, 100);
        primaryStage.setTitle("Viga!");
        primaryStage.setScene(scene);
    }
}
