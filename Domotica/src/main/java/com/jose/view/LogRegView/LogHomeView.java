package com.jose.view.LogRegView;

import com.jose.controller.LogReg.LogRegController;
import com.jose.view.DefaultView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.application.Application.launch;

public class LogHomeView extends DefaultView {
    private static LogRegController controller;

    public LogHomeView(){}

    public LogHomeView(LogRegController _controller){
        this.controller = _controller;
    }

    public static void initComponents() throws Exception {
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("x");
        primaryStage.initStyle(StageStyle.DECORATED);

        StackPane root = new StackPane();
        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add("css/root.css");
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
