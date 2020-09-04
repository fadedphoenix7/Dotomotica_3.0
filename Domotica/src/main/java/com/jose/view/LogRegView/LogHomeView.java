package com.jose.view.LogRegView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jose.controller.LogReg.LogRegController;
import com.jose.model.schemas.User;
import com.jose.view.DefaultView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.util.ArrayList;


public class LogHomeView extends DefaultView {
    private static LogRegController controller = new LogRegController();

    public LogHomeView(){}

    public static Parent initLoginView() throws IOException {
        FXMLLoader loaderLogin = new FXMLLoader();
        loaderLogin.setLocation(LogHomeView.class.getResource("/ViewFormat/LogRegFormat/Login.fxml"));
        Parent login = loaderLogin.load();
        login.getStylesheets().add("css/root.css");

        Button loginButton = (Button) login.getChildrenUnmodifiable().get(6);
        loginButton.setDisable(true);
        Button createButton = (Button) login.getChildrenUnmodifiable().get(9);
        createButton.setDisable(true);

        Label emailLabel = (Label) login.getChildrenUnmodifiable().get(7);
        TextField emailField = (TextField) login.getChildrenUnmodifiable().get(1);
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.emailValidation(newValue,emailLabel,loginButton);
        });

        PasswordField passwordField = (PasswordField) login.getChildrenUnmodifiable().get(2);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.passwordValidation(newValue,loginButton);
        });

        TextField codeField = (TextField) login.getChildrenUnmodifiable().get(8);
        codeField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.validateHouseCode(newValue,createButton);
        });
        return login;
    }

    public static Parent initRegisterView(String houseName) throws IOException {
        FXMLLoader loaderRegister = new FXMLLoader();
        loaderRegister.setLocation(LogHomeView.class.getResource("/ViewFormat/LogRegFormat/Register.fxml"));
        Parent register = loaderRegister.load();
        register.getStylesheets().add("css/root.css");

        Pane registerPaneCode = (Pane) register.getChildrenUnmodifiable().get(0);

        registerDisableView(true, register);

        Label houseLabel = (Label)registerPaneCode.getChildren().get(7);
        houseLabel.setText(houseName);

        Button verifyEmailButton = (Button) registerPaneCode.getChildren().get(3);


        Label emailLabel = (Label) registerPaneCode.getChildrenUnmodifiable().get(8);
        TextField emailField = (TextField) registerPaneCode.getChildrenUnmodifiable().get(0);
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.emailValidationRegister(newValue,emailLabel,verifyEmailButton);
        });

        return register;
    }

    public static void registerDisableView(boolean disable, Parent register){
        Pane registerPaneCode = (Pane) register.getChildrenUnmodifiable().get(0);

        TextField nameField = (TextField) registerPaneCode.getChildren().get(1);
        nameField.setDisable(disable);

        TextField lastnameField = (TextField) registerPaneCode.getChildren().get(2);
        lastnameField.setDisable(disable);

        PasswordField passwordField = (PasswordField) registerPaneCode.getChildren().get(5);
        passwordField.setDisable(disable);

        Button registerButton = (Button) registerPaneCode.getChildren().get(4);
        registerButton.setDisable(disable);
    }

    public static Parent initRegisterHouseView(String code) throws IOException {
        FXMLLoader loaderRegisterHouse = new FXMLLoader();
        loaderRegisterHouse.setLocation(LogHomeView.class.getResource("/ViewFormat/LogRegFormat/RegisterHouse.fxml"));
        Parent registerHouse = loaderRegisterHouse.load();
        registerHouse.getStylesheets().add("css/root.css");

        Pane registerHousePaneCode = (Pane) registerHouse.getChildrenUnmodifiable().get(0);
        TextField codeTxt = (TextField) registerHousePaneCode.getChildren().get(0);
        codeTxt.setText(code);
        codeTxt.setDisable(true);

        return registerHouse;
    }

    public static Parent initRegisterCodeView() throws IOException {
        FXMLLoader loaderRegisterCode = new FXMLLoader();
        loaderRegisterCode.setLocation(LogHomeView.class.getResource("/ViewFormat/LogRegFormat/RegisterCode.fxml"));
        Parent registerCode = loaderRegisterCode.load();
        registerCode.getStylesheets().add("css/root.css");

        Pane registerPaneCode = (Pane) registerCode.getChildrenUnmodifiable().get(0);
        Button registerCodeButton = (Button) registerPaneCode.getChildrenUnmodifiable().get(3);
        registerCodeButton.setDisable(true);

        TextField registerCodeLabel = (TextField) registerPaneCode.getChildrenUnmodifiable().get(1);
        registerCodeLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.validateCodeToRegister(newValue,registerCodeButton);
        });

        return registerCode;
    }

    public static Parent selectUserView(ArrayList<User> users) throws IOException {
        FXMLLoader loaderSelectUser = new FXMLLoader();
        loaderSelectUser.setLocation(LogHomeView.class.getResource("/ViewFormat/LogRegFormat/ListUsersLogView.fxml"));
        Parent ListUserView = loaderSelectUser.load();
        ListUserView.getStylesheets().add("css/root.css");

        JFXListView<User> userList = (JFXListView<User>) ListUserView.getChildrenUnmodifiable().get(0);

        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        list.addAll(users);
        userList.getItems().addAll(list);
        activateSelectedUserButton(true, ListUserView);
        return ListUserView;
    }

    public static void activateSelectedUserButton(boolean disable, Parent ListUserView){
        JFXButton selectUserLogin = (JFXButton) ListUserView.getChildrenUnmodifiable().get(3);
        selectUserLogin.setDisable(disable);
    }
}
