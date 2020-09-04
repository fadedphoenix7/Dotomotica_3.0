package com.jose.view.HomeView;

import com.jfoenix.controls.*;
import com.jose.controller.Controller;
import com.jose.controller.Home.DeviceController;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

public class DeviceView {

    public static void changeDeviceCreateView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(DeviceView.initDeviceCreateView());
    }

    public static void changeDeviceView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(initDeviceInfoView());
    }

    public static void changeDeviceConfigView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(initConfigDeviceView());
    }

    //------------------- init views --------------------

    public static Pane initDeviceListView() throws IOException {
        FXMLLoader loaderDeviceList = new FXMLLoader();
        loaderDeviceList.setLocation(DeviceView.class.getResource("/ViewFormat/Home/DeviceListView.fxml"));
        Pane deviceListView = loaderDeviceList.load();
        deviceListView.getStylesheets().add("css/root.css");

        ListView listDevice = (ListView) deviceListView.getChildrenUnmodifiable().get(0);
        configListOfDevice(listDevice);

        Button addNewDeviceButton = (Button) deviceListView.getChildren().get(1);
        addNewDeviceButton.setVisible((Controller.getUserLogged().getUserRole() == UserRole.MODERATOR
                            || Controller.getUserLogged().getUserRole() == UserRole.ADMIN ));

        return deviceListView;
    }

    public static Pane initDeviceCreateView() throws IOException {
        DeviceController controller = new DeviceController();
        FXMLLoader loaderDeviceCreate = new FXMLLoader();
        loaderDeviceCreate.setLocation(DeviceView.class.getResource("/ViewFormat/Home/DeviceCreateView.fxml"));
        Pane deviceCreateView = loaderDeviceCreate.load();
//        deviceCreateView.getStylesheets().add("css/root.css");

        Button cancelButton = (Button) deviceCreateView.getChildren().get(0);
        cancelButton.setDisable(false);

        Button createButton = (Button) deviceCreateView.getChildren().get(1);
        createButton.setDisable(true);

        TextField nameField = (TextField) deviceCreateView.getChildren().get(2);
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.validateNameDevice(newValue,createButton);
        });

        ComboBox userPermissionList = (ComboBox) deviceCreateView.getChildren().get(4);
        ObservableList<UserRole> userRoleList = FXCollections.observableArrayList();
        userRoleList.removeAll();
        userRoleList.addAll(UserRole.values());
        userPermissionList.setItems(userRoleList);

        return deviceCreateView;
    }

    public static Pane initDeviceInfoView() throws IOException {
        Device device = DeviceController.getDevice();
        FXMLLoader loaderDeviceView = new FXMLLoader();
        loaderDeviceView.setLocation(DeviceView.class.getResource("/ViewFormat/Home/DeviceView.fxml"));
        Pane deviceView = loaderDeviceView.load();

        Image deviceImage = new Image("/Images/deviceIcon.png");
        ImageView imageViewIcon = (ImageView) deviceView.getChildren().get(0);
        imageViewIcon.setImage(deviceImage);

        Label deviceNameLabel = (Label) deviceView.getChildren().get(2);
        deviceNameLabel.setText(device.getNameDevice());

        Button configButton = (Button) deviceView.getChildren().get(3);
        configButton.setVisible(Controller.getUserLogged().getUserRole() == UserRole.MODERATOR
                                || Controller.getUserLogged().getUserRole() == UserRole.ADMIN);
        configButton.setOnAction( x ->{
            DeviceController.configDevice();
        });


        TextArea deviceDescriptions = (TextArea) deviceView.getChildren().get(4);
        deviceDescriptions.setText(device.getDescriptionDevice());

        Label deviceState = (Label) deviceView.getChildren().get(5);
        deviceState.setText(device.getState() +"");

        Button turnOnButton = (Button) deviceView.getChildren().get(6);
        turnOnButton.setOnAction( x -> {
            DeviceController.turnOnButton(device);
        });

        Button turnOffButton = (Button) deviceView.getChildren().get(7);
        turnOffButton.setOnAction( x -> {
            DeviceController.turnOffButton(device);
        });

        return deviceView;
    }

    public static Pane initConfigDeviceView() throws IOException {
        Device device = DeviceController.getDevice();
        FXMLLoader loaderConfigDeviceView = new FXMLLoader();
        loaderConfigDeviceView.setLocation(DeviceView.class.getResource("/ViewFormat/Home/DeviceConfigView.fxml"));
        Pane configDeviceView = loaderConfigDeviceView.load();

        TextField deviceName = (TextField) configDeviceView.getChildren().get(0);
        deviceName.setText(device.getNameDevice());

        ListView userCanUseList = (ListView) configDeviceView.getChildren().get(1);
        ObservableList<User> userCanUse = FXCollections.observableArrayList();
        userCanUse.removeAll();
        userCanUse.addAll(device.getUsers());
        userCanUseList.setItems(userCanUse);

        ListView usersInHouseList = (ListView) configDeviceView.getChildren().get(2);
        ObservableList<User> usersInHouse = FXCollections.observableArrayList();
        usersInHouse.removeAll();
        usersInHouse.addAll(DeviceController.getUsersInHouse());
        usersInHouseList.setItems(usersInHouse);

        ComboBox userRolePermission = (ComboBox) configDeviceView.getChildren().get(7);
        ObservableList<UserRole> userPermission = FXCollections.observableArrayList();
        userPermission.removeAll();
        userPermission.addAll(UserRole.values());
        userRolePermission.setItems(userPermission);
        userRolePermission.setValue(device.getUserRole());

        TextArea deviceDescription = (TextArea) configDeviceView.getChildren().get(8);
        deviceDescription.setText(device.getDescriptionDevice());

        return configDeviceView;
    }

    public static void configListOfDevice(ListView deviceList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Device> device = DeviceController.getListDevice(user.getID(), user.getId_house(), user.getUserRole());
        if(device != null && !device.isEmpty()){
            list.addAll(device);

            deviceList.setItems(list);
            deviceList.setCellFactory(x -> new DeviceCell(deviceList));
        }

    }

}
