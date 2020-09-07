package com.jose.view.HomeView;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jose.controller.Controller;
import com.jose.controller.Home.MainHouseController;
import com.jose.controller.LogReg.LogRegController;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;
import com.jose.view.DefaultView;
import com.jose.view.LogRegView.LogHomeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class MainView extends DefaultView {
    private static MainHouseController controller = new MainHouseController();
    private static double xOffset = 0;
    private static double yOffset = 0;

    public MainView(){}


    public static Parent initMainView() throws IOException {
        FXMLLoader loaderMainVIew = new FXMLLoader();
        loaderMainVIew.setLocation(MainView.class.getResource("/ViewFormat/Home/BaseHomeView.fxml"));
        Parent mainView = loaderMainVIew.load();
        mainView.getStylesheets().add("css/main.css");

        FXMLLoader loaderNavBar = new FXMLLoader();
        loaderNavBar.setLocation(MainView.class.getResource("/ViewFormat/Home/NavBarView.fxml"));
        Pane navBarView = loaderNavBar.load();
        navBarView.getStylesheets().add("css/main.css");

        User user =  Controller.getUserLogged();

        Button userButton = (Button) ((Pane) navBarView.getChildren().get(0)).getChildren().get(3);
        userButton.setVisible(user.getUserRole() == UserRole.ADMIN ||
                                user.getUserRole() == UserRole.MODERATOR);

        Button homeButton = (Button) ((Pane) navBarView.getChildren().get(0)).getChildren().get(4);
        homeButton.setVisible(user.getUserRole() == UserRole.ADMIN ||
                user.getUserRole() == UserRole.MODERATOR);

        Pane mainParent = (Pane) mainView.getChildrenUnmodifiable().get(1);
        mainParent.getChildren().add(initMHomeMainView());

        Pane topPane = (Pane) mainView.getChildrenUnmodifiable().get(0);

        Label homeLabel = (Label)  topPane.getChildren().get(1);
        homeLabel.setText("Bienvenido " + Controller.getUserLogged().getUserName());

        JFXDrawer navbar = (JFXDrawer) mainView.getChildrenUnmodifiable().get(2);
        navbar.setSidePane(navBarView);

        JFXHamburger hamburger = (JFXHamburger) topPane.getChildren().get(0);

        HamburgerBasicCloseTransition burgerTaskClose = new HamburgerBasicCloseTransition(hamburger);
        burgerTaskClose.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            burgerTaskClose.setRate(burgerTaskClose.getRate() * -1);
            burgerTaskClose.play();

            if(navbar.isOpened()){
                navbar.close();
                navbar.setVisible(false);

            }
            else{
                navbar.open();
                navbar.setVisible(true);
            }
        });

        MainHouseController.setNavbar(navbar);
        MainHouseController.setBurgerTaskClose(burgerTaskClose);


        MainHouseController.setMain(mainView);

        return mainView;
    }

    public static Pane initMHomeMainView() throws IOException {
        FXMLLoader loaderHomeMain = new FXMLLoader();
        loaderHomeMain.setLocation(MainView.class.getResource("/ViewFormat/Home/MainView.fxml"));
        Pane homeMainView = loaderHomeMain.load();
        homeMainView.getStylesheets().add("css/main.css");


        ListView deviceList = (ListView) homeMainView.getChildren().get(0);
        deviceListOnMain(deviceList);

        ListView areaList = (ListView) homeMainView.getChildren().get(1);
        areaListMain(areaList);

        return homeMainView;
    }

    public static void deviceListOnMain(ListView deviceList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Device> device = MainHouseController.getListOnDevice(user.getID(), user.getId_house(), user.getUserRole());
        if(device != null && !device.isEmpty()){
            list.addAll(device);
            deviceList.getItems().addAll(list);
            deviceList.setCellFactory( device1 -> new DeviceCell(deviceList, deviceList.getWidth() ));
        }
    }

    public static void areaListMain(ListView areaList){
        User user = Controller.getUserLogged();
        ObservableList list = FXCollections.observableArrayList();
        list.removeAll();
        ArrayList<Area> area = MainHouseController.getAreasFromUser(user.getID(), user.getId_house(), user.getUserRole());
        if(area != null && !area.isEmpty()){
            list.addAll(area);
            areaList.getItems().addAll(list);
            areaList.setCellFactory(area1 -> new AreaCell(areaList, areaList.getWidth() ));
        }
    }

    public static void changeDeviceView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(DeviceView.initDeviceListView());
    }

    public static void changeAreaView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(AreaVIew.initAreaListView());
    }

    public static void changeMainView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(initMHomeMainView());
    }

    public static void changeUsersView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(UserView.initUserListView());
    }

    public static void changeuserConfigView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(UserView.initConfigUserView());
    }

    public static void changeHouseConfigView(Parent mainScene) throws IOException {
        Pane  p = (Pane) mainScene.getChildrenUnmodifiable().get(1);
        p.getChildren().remove(0);
        p.getChildren().add(HouseView.initHouseView());
    }
}
