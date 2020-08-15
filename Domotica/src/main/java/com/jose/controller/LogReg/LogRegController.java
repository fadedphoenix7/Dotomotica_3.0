package com.jose.controller.LogReg;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialog;
import com.jose.model.Validation.StringValidation;
import com.jose.model.operations.LogRegFunctions;
import com.jose.model.schemas.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class LogRegController {

    @FXML
    private Button closeButton;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label emailLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button houseCodeButton;
    @FXML
    private Pane pane;

    private static boolean emailForm = false;
    private static boolean passwordForm = false;

    public LogRegController(){

    }

    public void registerNewHouse(){
        String code = LogRegFunctions.registerNewHouse();
        returnRegisterHouseCode(code);
    }

    public void returnRegisterHouseCode(String code){

    }

    @FXML
    private void closeWindow(ActionEvent event) {
        System.out.println("Close!!!!!!!!!!!!!!!!!!");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void login(ActionEvent event){
        String email = emailText.getText();
        String password = passwordText.getText();
        User userLogged = LogRegFunctions.login(email, password);
        System.out.println(userLogged);
    }

    @FXML
    public void create(ActionEvent event){
        JFXAlert alert = new JFXAlert();
        alert.setTitle("Codigo de Casa");
//        alert.setHeaderText("No se que sera");
        alert.setSize(150,60);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContent(new Button(""));
        alert.setAnimation(JFXAlertAnimation.NO_ANIMATION);
        alert.show();
        System.out.println("Registrado");
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
            emailTxt.setText("Correo no valido");
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
        if(!code.isEmpty() && code.length() == 15 && !haveSpace ){
            createButton.setDisable(false);
        }
        else {
            createButton.setDisable(true);
        }
    }


}
