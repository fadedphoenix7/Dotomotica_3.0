package com.jose.controller.LogReg;

import com.jose.model.Validation.StringValidation;
import com.jose.model.operations.LogRegFunctions;
import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import com.jose.view.PopupView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;


import java.util.ArrayList;
import java.util.HashMap;

public class LogRegController {

    @FXML
    private Button closeButton, loginButton,houseCodeButton,verifyEmailButton;
    @FXML
    private TextField emailText,homeCodeText, registerCodeText,nameText,lastNameText,houseNameTxt,houseRegistrationCode;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label emailLabel,houseName;
    @FXML
    private Pane pane;

    private static boolean emailForm = false, passwordForm = false;

    private static HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main;

    private static House houseToRegister;

    public LogRegController(){ }

    public static void setScene(Scene scene){
        main = scene;
    }

    public static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        main.setRoot( screenMap.get(name) );
        main.getStylesheets().add("css/root.css");
    }

    public void registerProcess(){
        activate("registerCode");

    }

    public void registerNewHouse(){
        String nameOfHouse = houseNameTxt.getText();
        String registrationCode = houseRegistrationCode.getText();
        House house = LogRegFunctions.registerNewHouse(nameOfHouse,registrationCode);
        PopupView p = new PopupView(house.getCode(),"Codigo de registro para nuevos usuarios" +
                "\n(No pierdas el codigo, no es reuperable)" +
                "\nCodigo:");
        configRegisterView(house.getName());
        activate("register");
        setHouseToRegister(house);
    }


    @FXML
    private void closeWindow(ActionEvent event) {
        System.out.println("Close!!!!!!!!!!!!!!!!!!");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void backToLogin() {
        activate("login");
    }

    @FXML
    public void login(ActionEvent event){
        String email = emailText.getText();
        String password = passwordText.getText();
        System.out.println(email + "  " + password);
        ArrayList<User> usersLogged = LogRegFunctions.login(email, password);
        System.out.println(usersLogged);
    }

    @FXML
    public void createNewHouse(ActionEvent event){
        String code =homeCodeText.getText();
        System.out.println("Aqui estoy");

        if(LogRegFunctions.registerNewHouseCode(code)) {
            Pane registerHousePane = screenMap.get("registerHouse");
            Pane registerHousePaneCode = (Pane) registerHousePane.getChildrenUnmodifiable().get(0);
            TextField codeTxt = (TextField) registerHousePaneCode.getChildren().get(0);
            codeTxt.setText(code);
            codeTxt.setDisable(true);
            activate("registerHouse");

        }
    }

    @FXML
    public void createCode(ActionEvent event){
        String msg = LogRegFunctions.registerNewRegistrationCode();
        PopupView pop = new PopupView(msg, "Codigo");
    }

    @FXML
    public void emailValidation(String email,Label emailTxt, Button _loginButton){
        boolean initSpace = StringValidation.noInitSpace(email);
        boolean haveSpace = StringValidation.noSpace(email);
        boolean haveArroba = StringValidation.arroba(email);

        if(!initSpace && !haveSpace && haveArroba){
            emailTxt.setText(" ");
            emailForm = true;
        }
        else{
            emailForm = false;
            emailTxt.setText("Correo invalido");
        }
        loginFormListerner(_loginButton);

    }

    @FXML
    public void loginFormListerner(Button _loginButton){
        if(emailForm && passwordForm){
            _loginButton.setDisable(false);
        }
        else {
            _loginButton.setDisable(true);
        }
    }

    @FXML
    public void passwordValidation(String password, Button _loginButton){
        boolean initSpace = StringValidation.noInitSpace(password);
        boolean haveSpace = StringValidation.noSpace(password);

        if(!initSpace && !haveSpace && !password.isEmpty()){
            passwordForm = true;
        }
        else{
            passwordForm = false;
        }
        loginFormListerner(_loginButton);

    }

    @FXML
    public void validateHouseCode(String code, Button createButton){
        boolean haveSpace = StringValidation.noSpace(code);
        if(!code.isEmpty() && code.length() == 20 && !haveSpace ){
            createButton.setDisable(false);
        }
        else {
            createButton.setDisable(true);
        }
    }

    @FXML
    public void registerUserByCode(){
        String codeToRegister = registerCodeText.getText();
        House house = LogRegFunctions.registerUserByCode(codeToRegister);
        if( house != null){
            configRegisterView(house.getName());
            activate("register");
            setHouseToRegister(house);
        }
        else{
            PopupView error = new PopupView("Codigo incorrecto");
        }
    }

    @FXML
    public void validateCodeToRegister(String code, Button confirmButton){
        boolean haveSpace = StringValidation.noSpace(code);
        if(code.length() == 15 && !haveSpace && !code.isEmpty()){
            confirmButton.setDisable(false);
        }
        else {
            confirmButton.setDisable(true);
        }
    }

    public void configRegisterView(String name){
        Pane registerPane = screenMap.get("register");
        Pane registerPaneCode = (Pane) registerPane.getChildrenUnmodifiable().get(0);

        registerDisableView(true);

        Label houseLabel = (Label)registerPaneCode.getChildren().get(7);
        houseLabel.setText(name);

        Button verifyEmailButton = (Button) registerPaneCode.getChildren().get(3);


        Label emailLabel = (Label) registerPaneCode.getChildrenUnmodifiable().get(8);
        TextField emailField = (TextField) registerPaneCode.getChildrenUnmodifiable().get(0);
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.emailValidationRegister(newValue,emailLabel,verifyEmailButton);
        });
    }

    @FXML
    public void emailValidationRegister(String email,Label emailTxt, Button _verifyEmailButton){
        boolean initSpace = StringValidation.noInitSpace(email);
        boolean haveSpace = StringValidation.noSpace(email);
        boolean haveArroba = StringValidation.arroba(email);
        registerDisableView(true);
        if(!initSpace && !haveSpace && haveArroba){
            emailTxt.setText(" ");
            _verifyEmailButton.setDisable(false);
        }
        else{
            emailTxt.setText("Email no valido");
            _verifyEmailButton.setDisable(true);

        }

    }

    @FXML
    public void verifyIsEmailRegister(){
        String email = emailText.getText();
        boolean isRegister = LogRegFunctions.isEmailRegistered(email, getHouseToRegister().getID());
        System.out.println(isRegister);
        registerDisableView(isRegister);


    }

    public void registerDisableView(boolean disable){
        Pane registerPane = screenMap.get("register");
        Pane registerPaneCode = (Pane) registerPane.getChildrenUnmodifiable().get(0);

        TextField nameField = (TextField) registerPaneCode.getChildren().get(1);
        nameField.setDisable(disable);

        TextField lastnameField = (TextField) registerPaneCode.getChildren().get(2);
        lastnameField.setDisable(disable);

        PasswordField passwordField = (PasswordField) registerPaneCode.getChildren().get(5);
        passwordField.setDisable(disable);

        Button registerButton = (Button) registerPaneCode.getChildren().get(4);
        registerButton.setDisable(disable);
    }

    public void registerNewUser(){
        String name = nameText.getText(), lastName = lastNameText.getText(),
                password = passwordText.getText(), email = emailText.getText();
        boolean validName = !StringValidation.noInitSpace(name) && StringValidation.noNumbers(name)
                && !name.isEmpty();
        boolean validLastName = !StringValidation.noInitSpace(lastName) && StringValidation.noNumbers(lastName)
                && !lastName.isEmpty();
        boolean validPassword = !StringValidation.noInitSpace(password) && !StringValidation.noValidChars(password)
                && !StringValidation.noSpace(password) && !password.isEmpty() && password.length() >= 8;
        System.out.println("name:" + validName + " lastName: " + validLastName + " pass: " + validPassword);
        if(validName){
            if(validLastName){
                if(validPassword){
                    LogRegFunctions.registerNewUser(name,lastName,email,password,getHouseToRegister().getID());
                    PopupView error = new PopupView("Usuario Registrado Correctamente");
                    activate("login");
                }else{
                    PopupView error = new PopupView("Password invalido. Escriba otro (Longitud o caracteres incorrectos)");
                }
            }else{
                PopupView error = new PopupView("Apellido invalido. Escriba otro (Sin numeros ni espacios al inicio)");
            }
        }else{
            PopupView error = new PopupView("Nombre invalido. Escriba otro (Sin numero ni espacios al inicio)");
        }
    }

    public static House getHouseToRegister() {
        return houseToRegister;
    }

    public static void setHouseToRegister(House houseToRegister) {
        LogRegController.houseToRegister = houseToRegister;
    }

}
