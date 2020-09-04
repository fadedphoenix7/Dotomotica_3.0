package com.jose.controller.Home;

import com.jose.controller.Controller;
import com.jose.model.Validation.StringValidation;
import com.jose.model.operations.UserFunctions;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import com.jose.view.HomeView.MainView;
import com.jose.view.PopupView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class UserController {

    @FXML
    private ListView listOfUsers;
    @FXML
    private TextField nameField, lastNameField, emailFIeld;
    @FXML
    private PasswordField passwordField;

    public void backToHome(){
        Parent mainParent = MainHouseController.getMain();
        try {
            MainView.changeMainView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //-------------- views actions ------------

    public void deleteUser(){
        User user = (User) listOfUsers.getSelectionModel().getSelectedItem();
        System.out.println(user);
    }

    public void updateUser(){
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        try{
            if(name.isEmpty() || StringValidation.noInitSpace(name)) throw new Exception("nombre invalido");
            if(lastName.isEmpty() || StringValidation.noInitSpace(lastName)) throw new Exception("nombre invalido");
            User user = Controller.getUserLogged();
            user.setUserName(name);
            user.setLastName(lastName);
            UserFunctions.updateUser(user);
        }
        catch (Exception e) {
            new PopupView(e.getMessage());
        }
    }

    public static void updateUserFromList(User user){
        UserFunctions.updateUser(user);
    }

    //-------------- calls to DB ---------------
    public static ArrayList<User> getUsersFromHouse(int userID, int houseID, UserRole role){
        return UserFunctions.getUsersFromHouse(userID, houseID, role);
    }
}
