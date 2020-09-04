package com.jose.view.HomeView;

import com.jose.controller.Controller;
import com.jose.controller.Home.UserController;
import com.jose.model.schemas.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class UserView {

    public static Pane initUserListView() throws IOException {
        FXMLLoader loaderUserList = new FXMLLoader();
        loaderUserList.setLocation(UserView.class.getResource("/ViewFormat/Home/UserListView.fxml"));
        Pane userListView = loaderUserList.load();
        userListView.getStylesheets().add("css/root.css");

        ListView listUsers = (ListView) userListView.getChildren().get(0);
        configUserList(listUsers);

        return userListView;
    }

    public static Pane initConfigUserView() throws IOException {
        FXMLLoader loaderConfigUser = new FXMLLoader();
        loaderConfigUser.setLocation(UserView.class.getResource("/ViewFormat/Home/UserConfigView.fxml"));
        Pane configUserView = loaderConfigUser.load();
        configUserView.getStylesheets().add("css/root.css");

        User user = Controller.getUserLogged();

        TextField userName = (TextField) configUserView.getChildren().get(0);
        userName.setText(user.getUserName());

        TextField userLastName = (TextField) configUserView.getChildren().get(1);
        userLastName.setText(user.getLastName());

        TextField userEmail = (TextField) configUserView.getChildren().get(2);
        userEmail.setText(user.getEmail());

        PasswordField password = (PasswordField) configUserView.getChildren().get(3);
        password.setText(user.getPassword());



        return configUserView;
    }

    public static  void configUserList(ListView userList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<User> users = UserController.getUsersFromHouse(user.getID(), user.getId_house(), user.getUserRole());
        if(users != null && !users.isEmpty()){
            list.addAll(users);
            userList.setItems(list);
            userList.setCellFactory(user_ -> new UserCell());
        }
    }
}
