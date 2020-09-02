package com.jose.controller.Home;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jose.controller.Controller;
import com.jose.model.operations.AreaFunctions;
import com.jose.model.operations.DeviceFunctions;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import com.jose.model.schemas.UserRole;
import com.jose.view.DefaultView;
import com.jose.view.HomeView.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class MainHouseController {
    @FXML
    private Pane mainPane;
    @FXML
    private JFXHamburger hamburguer;
    @FXML
    private static JFXDrawer navbar;
    private static HamburgerBasicCloseTransition burgerTaskClose;

    private static Parent main;

    public static Parent getMain() {
        return main;
    }

    public static void setMain(Parent main) {
        MainHouseController.main = main;
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        System.out.println("Close!!!!!!!!!!!!!!!!!!");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void logOut(){
        DefaultView.setLoginView();
        Controller.setUserLogged(null);
    }

    public static ArrayList<Device> getListOnDevice(int userID, int houseID, UserRole role){
        return DeviceFunctions.getDevicesOn(userID, houseID, role);
    }

    public static ArrayList<Area> getAreasFromUser(int userID, int houseID, UserRole role){
        return AreaFunctions.getAreasManage(userID, houseID, role);
    }



    public void closeNavbar(){
        burgerTaskClose.setRate(burgerTaskClose.getRate() * -1);
        burgerTaskClose.play();
        navbar.close();
        navbar.setVisible(false);
    }

    public static JFXDrawer getNavbar() {
        return navbar;
    }

    public static void setNavbar(JFXDrawer navbar) {
        MainHouseController.navbar = navbar;
    }

    public static HamburgerBasicCloseTransition getBurgerTaskClose() {
        return burgerTaskClose;
    }

    public static void setBurgerTaskClose(HamburgerBasicCloseTransition burgerTaskClose) {
        MainHouseController.burgerTaskClose = burgerTaskClose;
    }

    //----------- init views --------------------

    public void initDeviceList(){
        try {
            closeNavbar();
            MainView.changeDeviceView(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initAreaList(){
        try {
            closeNavbar();
            MainView.changeAreaView(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMainView(){
        try {
            closeNavbar();
            MainView.changeMainView(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
