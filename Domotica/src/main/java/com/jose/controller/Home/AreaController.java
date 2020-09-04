package com.jose.controller.Home;

import com.jose.controller.Controller;
import com.jose.model.Validation.StringValidation;
import com.jose.model.operations.AreaFunctions;
import com.jose.model.operations.DeviceFunctions;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.UserRole;
import com.jose.view.HomeView.AreaVIew;
import com.jose.view.HomeView.DeviceView;
import com.jose.view.HomeView.MainView;
import com.jose.view.PopupView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class AreaController {

    private static Area area;

    @FXML
    private TextField nameAreaField;
    @FXML
    private ComboBox roleComboBox;
    @FXML
    private ListView areaListArea, areaListHome, deviceListArea, deviceListHome;

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

    public void configArea(){
        Parent mainParent = MainHouseController.getMain();
        try {
            AreaVIew.changeAreaConfigView(mainParent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //------------ view actions -------------

    public void createNewArea(){
        String areaName = nameAreaField.getText();
        UserRole role = (UserRole) roleComboBox.getValue();
        AreaFunctions.addArea(areaName, role, Controller.getUserLogged().getId_house());
        backToAreaList();
    }

    public void addAreaToArea(){
        Area chidArea = (Area) areaListHome.getSelectionModel().getSelectedItem();
        AreaFunctions.areaAddArea(area, chidArea);
        configArea();
    }

    public void addDeviceToArea(){
        Device chidArea = (Device) deviceListHome.getSelectionModel().getSelectedItem();
        AreaFunctions.areaAdDevice(area, chidArea);
        configArea();
    }

    public void removeAreaToArea(){
        Area chidArea = (Area) areaListArea.getSelectionModel().getSelectedItem();
        AreaFunctions.removeAreaToArea(area, chidArea);
        configArea();
    }

    public void removeDeviceToArea(){
        Device device = (Device) deviceListArea.getSelectionModel().getSelectedItem();
        AreaFunctions.removeDeviceToArea(area, device);
        configArea();
    }

    public void updateArea(){
        String name = nameAreaField.getText();
        UserRole role = (UserRole) roleComboBox.getValue();
        if(name.isEmpty() || StringValidation.noInitSpace(name)){
            new PopupView("Nombre Invalido");
        }
        else{
            area.setNameArea(name);
            area.setUserRole(role);
            AreaFunctions.updateArea(area);
        }
    }

    public void turnOnArea(){
        AreaFunctions.setPassedArea(new ArrayList<>());
        AreaFunctions.turnOnArea(area);
        enterArea();
    }

    public void turnOffArea(){
        AreaFunctions.setPassedArea(new ArrayList<>());
        AreaFunctions.turnOffArea(area);
        enterArea();
    }

    public static void turnOnArea(Area area){
        AreaFunctions.setPassedArea(new ArrayList<>());
        AreaFunctions.turnOnArea(area);
        enterArea();
    }

    public static void turnOffArea(Area area){
        AreaFunctions.setPassedArea(new ArrayList<>());
        AreaFunctions.turnOffArea(area);
        enterArea();
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

    public static ArrayList<Area> getAreaFromHouseNotArea(){
        return AreaFunctions.getAreaFromHouseNotArea(area.getID(), area.getID_house());
    }

    public static ArrayList<Device> getDeviceFromHouseNotArea(){
        return DeviceFunctions.getDevicesFromHouseNotArea(area.getID(), area.getID_house());
    }

    // ------------- set and get ------------

    public static Area getArea() {
        return area;
    }

    public static void setArea(Area area) {
        AreaController.area = area;
    }
}
