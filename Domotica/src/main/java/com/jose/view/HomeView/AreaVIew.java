package com.jose.view.HomeView;

import com.jose.controller.Controller;
import com.jose.controller.Home.AreaController;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class AreaVIew {

    //---------------- change views -------------

    public static void changeCreateView(Parent mainParent){
        try {
            Pane  p = (Pane) mainParent.getChildrenUnmodifiable().get(1);
            p.getChildren().remove(0);
            p.getChildren().add(initCreateAreaView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeAreaView(Parent mainParent){
        try {
            Pane  p = (Pane) mainParent.getChildrenUnmodifiable().get(1);
            p.getChildren().remove(0);
            p.getChildren().add(initAreaView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeAreaConfigView(Parent mainParent){
        try {
            Pane  p = (Pane) mainParent.getChildrenUnmodifiable().get(1);
            p.getChildren().remove(0);
            p.getChildren().add(initConfigAreaView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------- init vies ----------------
    public static Pane initAreaListView() throws IOException {
        FXMLLoader loaderAreaList = new FXMLLoader();
        loaderAreaList.setLocation(DeviceView.class.getResource("/ViewFormat/Home/AreaListView.fxml"));
        Pane areaListView = loaderAreaList.load();
        areaListView.getStylesheets().add("css/root.css");

        ListView<Device> listDevice = (ListView) areaListView.getChildrenUnmodifiable().get(0);
        configListArea(listDevice);

        Button createButton = (Button) areaListView.getChildren().get(1);
        createButton.setVisible(Controller.getUserLogged().getUserRole() == UserRole.ADMIN ||
                Controller.getUserLogged().getUserRole() == UserRole.MODERATOR);

        return areaListView;
    }

    public static Pane initCreateAreaView() throws IOException {
        FXMLLoader loaderCreateView = new FXMLLoader();
        loaderCreateView.setLocation(DeviceView.class.getResource("/ViewFormat/Home/AreaCreateView.fxml"));
        Pane areaCreateView = loaderCreateView.load();
        areaCreateView.getStylesheets().add("css/root.css");

        ComboBox userPermissionList = (ComboBox) areaCreateView.getChildren().get(1);
        ObservableList<UserRole> userRoleList = FXCollections.observableArrayList();
        userRoleList.removeAll();
        userRoleList.addAll(UserRole.values());
        userPermissionList.setItems(userRoleList);

        return areaCreateView;
    }

    public static Pane initAreaView() throws IOException {
        FXMLLoader loaderArea = new FXMLLoader();
        loaderArea.setLocation(DeviceView.class.getResource("/ViewFormat/Home/AreaView.fxml"));
        Pane areaView = loaderArea.load();
        areaView.getStylesheets().add("css/root.css");

        Area area = AreaController.getArea();
        Image image = new Image("/Images/areaIcon.png");
        ImageView imageIcon = (ImageView) areaView.getChildren().get(0);
        imageIcon.setImage(image);

        Label labelAreaName = (Label) areaView.getChildren().get(2);
        labelAreaName.setText(area.getNameArea());

        ListView areasList = (ListView) areaView.getChildren().get(3);
        ListView deviceList = (ListView) areaView.getChildren().get(4);

        configAreasOfArea(areasList);
        configDevicesOfArea(deviceList);

        Button configButton = (Button) areaView.getChildren().get(10);
        configButton.setVisible(Controller.getUserLogged().getUserRole() == UserRole.MODERATOR ||
                Controller.getUserLogged().getUserRole() == UserRole.ADMIN);

        return areaView;
    }

    public static Pane initConfigAreaView() throws IOException {
        FXMLLoader loaderConfigArea = new FXMLLoader();
        loaderConfigArea.setLocation(DeviceView.class.getResource("/ViewFormat/Home/AreaConfigView.fxml"));
        Pane configView = loaderConfigArea.load();
        configView.getStylesheets().add("css/root.css");

        Area area = AreaController.getArea();

        TextField nameAreaField = (TextField) configView.getChildren().get(0);
        nameAreaField.setText(area.getNameArea());

        ComboBox userPermissionList = (ComboBox) configView.getChildren().get(1);
        ObservableList<UserRole> userRoleList = FXCollections.observableArrayList();
        userRoleList.removeAll();
        userRoleList.addAll(UserRole.values());
        userPermissionList.setItems(userRoleList);
        userPermissionList.setEditable(false);
        userPermissionList.setValue(area.getUserRole());

        ListView listAreasFromArea = (ListView) configView.getChildren().get(2);
        ListView listAreasFromHouse = (ListView) configView.getChildren().get(3);
        ListView listDeviceFromArea = (ListView) configView.getChildren().get(4);
        ListView listDevicesFromHouse = (ListView) configView.getChildren().get(5);

        configAreasOfAreaConfig(listAreasFromArea);
        configAreaOfHouseNotArea(listAreasFromHouse);
        configDevicesOfAreaConfig(listDeviceFromArea);
        configDeviceOfHouseNotArea(listDevicesFromHouse);



        return configView;
    }

    //----------------- config views -------------

    private static void configListArea(ListView listArea){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Area> areas = AreaController.getAreaFromHouse(user.getID(), user.getId_house(), user.getUserRole());
        if(areas != null && !areas.isEmpty()){
            list.addAll(areas);
            listArea.setItems(list);
            listArea.setCellFactory(x -> new AreaCell(listArea));
        }
    }
    private static void configAreasOfArea(ListView listArea){
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Area> areas = AreaController.getAreaOfArea();
        if(areas != null && !areas.isEmpty()){
            list.addAll(AreaController.getArea().getAreas_child());
            listArea.setItems(list);
            listArea.setCellFactory(x -> new AreaCell(listArea));
        }
    }
    private static void configDevicesOfArea(ListView listArea){
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Device> areas = AreaController.getDeviceFromArea();
        if(areas != null && !areas.isEmpty()){
            list.addAll(AreaController.getArea().getDevices());

            listArea.setItems(list);
            listArea.setCellFactory(x -> new DeviceCell(listArea));
        }
    }

    private static void configAreasOfAreaConfig(ListView listArea){
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        list.addAll(AreaController.getArea().getAreas_child());

        listArea.setItems(list);
    }
    private static void configDevicesOfAreaConfig(ListView listArea){
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        list.addAll(AreaController.getArea().getDevices());

        listArea.setItems(list);
    }

    private static void configDeviceOfHouseNotArea(ListView listArea){
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Device> areas = AreaController.getDeviceFromHouseNotArea();
        if(areas != null && !areas.isEmpty()){
            list.addAll(areas);

            listArea.setItems(list);
//            listArea.setCellFactory(x -> new DeviceCell());
        }
    }

    private static void configAreaOfHouseNotArea(ListView listArea){
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Area> areas = AreaController.getAreaFromHouseNotArea();
        if(areas != null && !areas.isEmpty()){
            list.addAll(areas);

            listArea.setItems(list);
//            listArea.setCellFactory(x -> new AreaCell());
        }
    }


}
