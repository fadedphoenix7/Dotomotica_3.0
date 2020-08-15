package com.jose.controller;

import com.jose.controller.LogReg.LogRegController;
import com.jose.view.LogRegView.LogHomeView;

public class Controller {
    private LogRegController logRegController;
    private LogHomeView logHomeView;

    public Controller(){
        logRegController = new LogRegController();
        logHomeView = new LogHomeView(logRegController);

    }

}
