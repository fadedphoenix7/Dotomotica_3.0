package com.jose.controller.Home;

import com.jose.controller.Controller;
import com.jose.model.Validation.StringValidation;
import com.jose.model.crud.HouseCRUD;
import com.jose.model.operations.AreaFunctions;
import com.jose.model.operations.DeviceFunctions;
import com.jose.model.operations.HouseFunctions;
import com.jose.model.operations.UserFunctions;
import com.jose.model.schemas.*;
import com.jose.view.HomeView.MainView;
import com.jose.view.PopupView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class HouseController {
    private static House house;

    @FXML
    private TextField nameField;
    @FXML
    private ListView deviceList, areaList, userList;

    //---------- views functions -----------

    public void backToHome(){
        Parent mainParent = MainHouseController.getMain();
        try {
            MainView.changeMainView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHome(){
        String name = nameField.getText();
        try{
            if(name.isEmpty() || StringValidation.noInitSpace(name)) throw new Exception("Nombre no valido");
            house.setName(name);
            HouseFunctions.update(house);
        }
        catch (Exception e) {
//            new PopupView(e.getMessage());
            e.printStackTrace();
        }

    }

    public void removeDevice(){
        Device device = (Device) deviceList.getSelectionModel().getSelectedItem();
        if(device != null){
            DeviceFunctions.deleteDevice(device, Controller.getUserLogged().getUserRole());
            updateView();
        }
    }

    public void removeArea(){
        Area area = (Area) areaList.getSelectionModel().getSelectedItem();
        if(area != null){
            AreaFunctions.deleteArea(area.getID(), Controller.getUserLogged().getUserRole());
            updateView();
        }
    }

    public void removeUser(){
        User user = (User) userList.getSelectionModel().getSelectedItem();
        if(user != null){
            UserFunctions.deleteUser(user, house.getID());
            updateView();
        }
    }

    public void updateView(){
        new MainHouseController().initConfigHouse();
    }

    //---------- calls to DB ---------------

    public static ArrayList<Device> getDevicesFromHouse(int houseID, int userID, UserRole role){
        return DeviceController.getListDevice(userID, houseID, role);
    }

    public static ArrayList<Area> getAreasFromHouse(int houseID, int userID, UserRole role){
        return AreaController.getAreaFromHouse(userID, houseID, role);
    }

    //---------- setters and getters -------

    public static House getHouse() {
        return house;
    }

    public static void setHouse(House house) {
        HouseController.house = house;
    }
}
