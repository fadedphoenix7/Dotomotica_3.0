package com.jose.controller;

import com.jose.controller.Home.MainHouseController;
import com.jose.controller.LogReg.LogRegController;
import com.jose.model.schemas.User;
import com.jose.view.DefaultView;
import com.jose.view.HomeView.MainView;
import com.jose.view.LogRegView.LogHomeView;

public class Controller {

    private static User userLogged;

    public Controller(){
        new DefaultView(1);
    }

    public static void initHomeView(){
        new MainView(new MainHouseController());
    }

    public static User getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(User userLogged) {
        Controller.userLogged = userLogged;
    }
}
