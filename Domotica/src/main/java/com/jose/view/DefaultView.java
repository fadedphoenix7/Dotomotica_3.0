package com.jose.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DefaultView extends Application {
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
    private final static String ROOTCSS = "css/root.css";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.DECORATED);

        StackPane root = new StackPane();
        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add(ROOTCSS);
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
