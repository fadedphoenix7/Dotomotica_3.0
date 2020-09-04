package com.jose.view.HomeView;

import com.jose.controller.Controller;
import com.jose.controller.Home.HouseController;
import com.jose.controller.Home.UserController;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.House;
import com.jose.model.schemas.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class HouseView {

    public static Pane initHouseView() throws IOException {
        FXMLLoader loeaderHouse = new FXMLLoader();
        loeaderHouse.setLocation(UserView.class.getResource("/ViewFormat/Home/HomeConfigView.fxml"));
        Pane houseConfigView = loeaderHouse.load();
        houseConfigView.getStylesheets().add("css/root.css");

        House house = HouseController.getHouse();

        TextField nameHoseText = (TextField) houseConfigView.getChildren().get(0);
        nameHoseText.setText(house.getName());

        TextField registartionCOde = (TextField) houseConfigView.getChildren().get(1);
        registartionCOde.setText(house.getCode());

        ListView devicesList = (ListView) houseConfigView.getChildren().get(2);
        ListView areasList = (ListView) houseConfigView.getChildren().get(3);
        ListView usersList = (ListView) houseConfigView.getChildren().get(4);

        configDeviceList(devicesList);
        configAreaList(areasList);
        configUserList(usersList);

        Image homeImage = new Image("/Images/homeIcon.png");
        ImageView imageIcon = (ImageView) houseConfigView.getChildren().get(7);
        imageIcon.setImage(homeImage);

        return houseConfigView;
    }

    public static  void configUserList(ListView userList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<User> users = UserController.getUsersFromHouse(user.getID(), user.getId_house(), user.getUserRole());
        if(users != null && !users.isEmpty()){
            list.addAll(users);
            userList.setItems(list);
//            userList.setCellFactory(user_ -> new UserCell());
        }
    }

    public static  void configDeviceList(ListView userList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Device> devices = HouseController.getDevicesFromHouse(user.getId_house(), user.getID(), user.getUserRole());
        if(devices != null && !devices.isEmpty()){
            list.addAll(devices);
            userList.setItems(list);
//            userList.setCellFactory(user_ -> new UserCell());
        }
    }

    public static  void configAreaList(ListView userList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Area> areas = HouseController.getAreasFromHouse(user.getId_house(), user.getID(), user.getUserRole());
        if(areas != null && !areas.isEmpty()){
            list.addAll(areas);
            userList.setItems(list);
//            userList.setCellFactory(user_ -> new UserCell());
        }
    }

}
