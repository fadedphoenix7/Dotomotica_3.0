package com.jose.controller.Home;

import com.jose.controller.Controller;
import com.jose.model.Validation.StringValidation;
import com.jose.model.operations.DeviceFunctions;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import com.jose.view.HomeView.DeviceView;
import com.jose.view.HomeView.MainView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;

public class DeviceController {

    @FXML
    private Button createDeviceButton;
    @FXML
    private TextField nameDeviceField;
    @FXML
    private TextArea descriptionDevice;
    @FXML
    private ComboBox deviceUserPermission;
    @FXML
    private ListView usersOnDevice, usersOnHouse;

    private static Device device;

    public void createNewDevice(){
        String name = nameDeviceField.getText(), description = descriptionDevice.getText();
        UserRole role = (UserRole) deviceUserPermission.getValue();
        User user = Controller.getUserLogged();
        DeviceFunctions.addDevice(name,description,user.getId_house(),null, role);
        backToDeviceListView();
    }



    public static ArrayList<Device> getListDevice(int userID, int houseID, UserRole role){
        return DeviceFunctions.getDeviceFromUser(userID, houseID, role);
    }

    public void createNewDeviceView(){
        Parent mainParent = MainHouseController.getMain();
        try {
            DeviceView.changeDeviceCreateView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enterDevice(Device device){
        Parent mainParent = MainHouseController.getMain();
        try {
            DeviceView.changeDeviceView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToDeviceListView(){
        Parent mainParent = MainHouseController.getMain();
        try {
            MainView.changeDeviceView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void configDevice(){
        Parent mainParent = MainHouseController.getMain();
        try {
            DeviceView.changeDeviceConfigView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // ------------- Device Funtions --------------
    public static void turnOnButton(Device device){
        DeviceFunctions.turnOnDevice(device);
    }

    public static void turnOffButton(Device device){
        DeviceFunctions.turnOffDevice(device);
    }

    public void addUser(){
        device.addUser((User) usersOnHouse.getSelectionModel().getSelectedItem());
        updateDevice();
        updateConfigUserList();
    }

    public void deleteUser(){
        device.getUsers().remove((User) usersOnDevice.getSelectionModel().getSelectedItem());
        updateDevice();
        updateConfigUserList();
    }
    // ------------- validations ------------------

    public void validateNameDevice(String name, Button createButton){
        createButton.setDisable(!name.isEmpty() && StringValidation.noInitSpace(name));
    }

    // ------------- calls to DB -----------------

    public static ArrayList<User> getUsersInHouse(){
        return DeviceFunctions.getUsersInHouse(device.getID_house(), device.getID());
    }

    public static ArrayList<User> getUsersInDevice(){
        return DeviceFunctions.getUsersInDevice(device.getID(), device.getID_house());
    }

    public void updateDevice(){
        String deviceName = nameDeviceField.getText();
        String deviceDescription = descriptionDevice.getText();
        UserRole role = (UserRole) deviceUserPermission.getValue();
        DeviceFunctions.updateDevice(device, deviceName, deviceDescription, role, device.getUsers());
    }

    public void deleteDevice(){
        DeviceFunctions.deleteDevice(device, Controller.getUserLogged().getUserRole());
    }

    // ------------- set and gets ----------------


    public static Device getDevice() {
        return device;
    }

    public static void setDevice(Device device) {
        DeviceController.device = device;
    }

    //------------- updates List ----------------
    public void updateConfigUserList(){
        configDevice();
    }
}
