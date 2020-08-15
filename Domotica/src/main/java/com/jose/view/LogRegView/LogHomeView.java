package com.jose.view.LogRegView;

import com.jfoenix.controls.JFXDialog;
import com.jose.controller.LogReg.LogRegController;
import com.jose.view.DefaultView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import static javafx.application.Application.launch;

public class LogHomeView extends DefaultView {
    private static LogRegController controller;
    private static double xOffset = 0;
    private static double yOffset = 0;

    public LogHomeView(){}

    public LogHomeView(LogRegController _controller){
        try {
            initComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initComponents() throws Exception {
        controller = new LogRegController();
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/example.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,800,600);

        System.out.println(root.getChildrenUnmodifiable().size());

        Button loginButton = (Button) root.getChildrenUnmodifiable().get(6);
        loginButton.setDisable(true);
        Button createButton = (Button) root.getChildrenUnmodifiable().get(9);
        //createButton.setDisable(true);

        Label emailLabel = (Label) root.getChildrenUnmodifiable().get(7);
//        System.out.println(emailLabel);
        TextField emailField = (TextField) root.getChildrenUnmodifiable().get(1);
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.emailValidation(newValue,emailLabel,loginButton);
        });

        PasswordField passwordField = (PasswordField) root.getChildrenUnmodifiable().get(3);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.passwordValidation(newValue,loginButton);
        });

        TextField codeField = (TextField) root.getChildrenUnmodifiable().get(8);
        codeField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.validateHouseCode(newValue,createButton);
        });

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        scene.getStylesheets().add("css/root.css");
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
