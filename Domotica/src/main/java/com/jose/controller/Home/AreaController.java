package com.jose.controller.Home;

import com.jose.controller.Controller;
import com.jose.model.operations.AreaFunctions;
import com.jose.model.operations.DeviceFunctions;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.UserRole;
import com.jose.view.HomeView.AreaVIew;
import com.jose.view.HomeView.DeviceView;
import com.jose.view.HomeView.MainView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class AreaController {

    private static Area area;

    @FXML
    private TextField nameAreaField;
    @FXML
    private ComboBox roleComboBox;

    //------------ createViews --------------
    public void createNewAreaView(){
        Parent mainParent = MainHouseController.getMain();
        try {
            AreaVIew.changeCreateView(mainParent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backToAreaList(){
        Parent mainParent = MainHouseController.getMain();
        try {
            MainView.changeAreaView(mainParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enterArea(){
        Parent mainParent = MainHouseController.getMain();
        try {
            AreaVIew.changeAreaView(mainParent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------ view actions -------------
    public void createNewArea(){
        String areaName = nameAreaField.getText();
        UserRole role = (UserRole) roleComboBox.getValue();
        System.out.println(areaName + "  " + role.name());
        AreaFunctions.addArea(areaName, role, Controller.getUserLogged().getId_house());
        backToAreaList();
    }

    public void configArea(){

    }

    //------------ calls to DB --------------
    public static ArrayList<Area> getAreaFromHouse(int userID, int houseID, UserRole role){
        return AreaFunctions.getAreasFromHouse(userID, houseID, role);
    }

    public static ArrayList<Area> getAreaOfArea(){
        return AreaFunctions.getAreasFromArea(area.getID());
    }

    public static ArrayList<Device> getDeviceFromArea(){
        return DeviceFunctions.getDevicesFromArea(area.getID());
    }

    // ------------- set and get ------------

    public static Area getArea() {
        return area;
    }

    public static void setArea(Area area) {
        AreaController.area = area;
    }
}
