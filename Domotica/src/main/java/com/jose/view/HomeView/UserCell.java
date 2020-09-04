package com.jose.view.HomeView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jose.controller.Home.AreaController;
import com.jose.controller.Home.UserController;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UserCell extends ListCell<User> {
    private User user;
    private HBox box = new HBox(10);
    private Label name = new Label();
    private Label lastName = new Label();
    private JFXComboBox<UserRole> roles = new JFXComboBox<>();
    private JFXButton updateButon = new JFXButton("Update");
    private Font font = new Font("Gadugi", 20 );
    private HBox pane = new HBox(15);

    public UserCell(){
        super();
        updateButon.setButtonType(JFXButton.ButtonType.RAISED);
        updateButon.setOnAction(e -> {
            user.setUserRole(roles.getValue());
            UserController.updateUserFromList(user);
        });

        ObservableList roleList = FXCollections.observableArrayList();
        roleList.removeAll();
        roleList.addAll(UserRole.USER, UserRole.MODERATOR);

        roles.setItems(roleList);
        roles.setStyle("-fx-font-size: 20");
        roles.setEditable(false);

        name.setFont(font);
        name.setPrefWidth(100);
        lastName.setFont(font);
        updateButon.setFont(font);
        pane.setPrefWidth(200);
        pane.getChildren().addAll(name, lastName);
        box.setPrefSize(500,40);
        box.getChildren().addAll(pane, roles, updateButon);
    }

    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        setText(null);
        setGraphic(null);
        if (user != null && !empty) {
            name.setText(user.getUserName());
            lastName.setText(user.getLastName());
            roles.setValue(user.getUserRole());
            this.user = user;
            setGraphic(box);
        }
    }

}
