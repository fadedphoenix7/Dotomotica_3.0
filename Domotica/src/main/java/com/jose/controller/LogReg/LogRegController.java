package com.jose.controller.LogReg;

import com.jose.Exceptions.UserException;
import com.jose.controller.Controller;
import com.jose.model.Validation.StringValidation;
import com.jose.model.operations.LogRegFunctions;
import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import com.jose.view.DefaultView;
import com.jose.view.LogRegView.LogHomeView;
import com.jose.view.PopupView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.util.ArrayList;

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
    @FXML
    private ListView usersList;

    private static boolean emailForm = false, passwordForm = false;

    private static Scene main;

    private static House houseToRegister;

    public LogRegController(){ }

    public static void setScene(Scene scene){
        main = scene;
    }

    public void registerProcess(){
        try {
            main.setRoot(LogHomeView.initRegisterCodeView());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerNewHouse(){
        String nameOfHouse = houseNameTxt.getText();
        String registrationCode = houseRegistrationCode.getText();
        House house = LogRegFunctions.registerNewHouse(nameOfHouse,registrationCode);
        new PopupView(house.getCode(),"Codigo de registro para nuevos usuarios" +
                "\n(No pierdas el codigo, no es reuperable)" +
                "\nCodigo:");
        try {
            main.setRoot(LogHomeView.initRegisterView(house.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setHouseToRegister(house);
    }


    @FXML
    private void closeWindow(ActionEvent event) {
        System.out.println("Close!!!!!!!!!!!!!!!!!!");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void backToLogin() {
        try {
            main.setRoot(LogHomeView.initLoginView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login(){
        String email = emailText.getText();
        String password = passwordText.getText();
        ArrayList<User> usersLogged = LogRegFunctions.login(email, password);
        try {
            if(usersLogged.isEmpty()) throw new UserException(3);
            if(usersLogged.size() == 1) {
                Controller.setUserLogged(usersLogged.get(0));
                DefaultView.setMainView();
            }else{
                main.setRoot(LogHomeView.selectUserView(usersLogged));
            }
        } catch (IOException | UserException e) {
            new PopupView(e.getMessage());
        }
    }

    public static void login(String email, String password){
//        String email = emailText.getText();
//        String password = passwordText.getText();
        ArrayList<User> usersLogged = LogRegFunctions.login(email, password);
        try {
            if(usersLogged.isEmpty()) throw new UserException(3);
            if(usersLogged.size() == 1) {
                Controller.setUserLogged(usersLogged.get(0));
                DefaultView.setMainView();
            }else{
                main.setRoot(LogHomeView.selectUserView(usersLogged));
            }
        } catch (IOException | UserException e) {
            new PopupView(e.getMessage());
        }
    }



    @FXML
    public void createNewHouse(){
        String code =homeCodeText.getText();
        if(LogRegFunctions.registerNewHouseCode(code)) {

            try {
                main.setRoot(LogHomeView.initRegisterHouseView(code));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    public void createCode(){
        String msg = LogRegFunctions.registerNewRegistrationCode();
        new PopupView(msg, "Codigo");
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
        boolean disable = !emailForm && !passwordForm;
        _loginButton.setDisable(disable);
    }

    @FXML
    public void passwordValidation(String password, Button _loginButton){
        boolean initSpace = StringValidation.noInitSpace(password);
        boolean haveSpace = StringValidation.noSpace(password);

        passwordForm = !initSpace && !haveSpace && !password.isEmpty();
        loginFormListerner(_loginButton);

    }

    @FXML
    public void validateHouseCode(String code, Button createButton){
        boolean haveSpace = StringValidation.noSpace(code), disable;
        disable = code.length() == 20 && !haveSpace;
        createButton.setDisable(!disable);
    }

    @FXML
    public void registerUserByCode(){
        String codeToRegister = registerCodeText.getText();
        House house = LogRegFunctions.registerUserByCode(codeToRegister);
        if( house != null){
            try {
                main.setRoot(LogHomeView.initRegisterView(house.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setHouseToRegister(house);
        }
        else{
            new PopupView("Codigo incorrecto");
        }
    }

    @FXML
    public void validateCodeToRegister(String code, Button confirmButton){
        boolean haveSpace = StringValidation.noSpace(code), disable;
        disable = !(code.length() == 15 && !haveSpace);
        confirmButton.setDisable(disable);
    }

    @FXML
    public void emailValidationRegister(String email,Label emailTxt, Button _verifyEmailButton){
        boolean initSpace = StringValidation.noInitSpace(email);
        boolean haveSpace = StringValidation.noSpace(email);
        boolean haveArroba = StringValidation.arroba(email);
        LogHomeView.registerDisableView(true, main.getRoot());
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
        LogHomeView.registerDisableView(isRegister, main.getRoot());

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
        if(validName){
            if(validLastName){
                if(validPassword){
                    LogRegFunctions.registerNewUser(name,lastName,email,password,getHouseToRegister().getID());
                    new PopupView("Usuario Registrado Correctamente");
                    try {
                        main.setRoot(LogHomeView.initLoginView());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    new PopupView("Password invalido. Escriba otro (Longitud o caracteres incorrectos)");
                }
            }else{
                new PopupView("Apellido invalido. Escriba otro (Sin numeros ni espacios al inicio)");
            }
        }else{
            new PopupView("Nombre invalido. Escriba otro (Sin numero ni espacios al inicio)");
        }
    }

    public void selectedItem(){
        LogHomeView.activateSelectedUserButton(false, main.getRoot());
    }

    @FXML
    public void loggedUser(ActionEvent event){
        Controller.setUserLogged((User) usersList.getSelectionModel().getSelectedItem());
        DefaultView.setMainView();
    }

    public static House getHouseToRegister() {
        return houseToRegister;
    }

    public static void setHouseToRegister(House houseToRegister) {
        LogRegController.houseToRegister = houseToRegister;
    }

}
