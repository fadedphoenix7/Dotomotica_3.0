package com.jose.view.LogRegView;

import com.jose.controller.LogReg.LogRegController;
import com.jose.view.DefaultView;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



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
        FXMLLoader loaderLogin = new FXMLLoader();
        FXMLLoader loaderRegister = new FXMLLoader();
        FXMLLoader loaderRegisterHouse = new FXMLLoader();
        FXMLLoader loaderRegisterCode = new FXMLLoader();

        loaderLogin.setLocation(getClass().getResource("/ViewFormat/Login.fxml"));
        Parent login = loaderLogin.load();
        login.getStylesheets().add("css/root.css");

        loaderRegister.setLocation(getClass().getResource("/ViewFormat/Register.fxml"));
        Parent register = loaderRegister.load();
        register.getStylesheets().add("css/root.css");

        loaderRegisterHouse.setLocation(getClass().getResource("/ViewFormat/RegisterHouse.fxml"));
        Parent registerHouse = loaderRegisterHouse.load();
        registerHouse.getStylesheets().add("css/root.css");

        loaderRegisterCode.setLocation(getClass().getResource("/ViewFormat/RegisterCode.fxml"));
        Parent registerCode = loaderRegisterCode.load();
        registerCode.getStylesheets().add("css/root.css");

        LogRegController.addScreen("login", (Pane)login);
        LogRegController.addScreen("register", (Pane)register);
        LogRegController.addScreen("registerHouse", (Pane)registerHouse);
        LogRegController.addScreen("registerCode", (Pane)registerCode);

        Pane registerPaneCode = (Pane) registerCode.getChildrenUnmodifiable().get(0);

        Scene scene = new Scene(login);

        Button loginButton = (Button) login.getChildrenUnmodifiable().get(6);
        loginButton.setDisable(true);
        Button createButton = (Button) login.getChildrenUnmodifiable().get(9);
        createButton.setDisable(true);
        Button registerCodeButton = (Button) registerPaneCode.getChildrenUnmodifiable().get(3);
        registerCodeButton.setDisable(true);

        Label emailLabel = (Label) login.getChildrenUnmodifiable().get(7);
        TextField emailField = (TextField) login.getChildrenUnmodifiable().get(1);
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.emailValidation(newValue,emailLabel,loginButton);
        });

        PasswordField passwordField = (PasswordField) login.getChildrenUnmodifiable().get(3);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.passwordValidation(newValue,loginButton);
        });

        TextField codeField = (TextField) login.getChildrenUnmodifiable().get(8);
        codeField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.validateHouseCode(newValue,createButton);
        });

        TextField registerCodeLabel = (TextField) registerPaneCode.getChildrenUnmodifiable().get(1);
        registerCodeLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.validateCodeToRegister(newValue,registerCodeButton);
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

        LogRegController.setScene(scene);

//        scene.getStylesheets().add("css/root.css");
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
