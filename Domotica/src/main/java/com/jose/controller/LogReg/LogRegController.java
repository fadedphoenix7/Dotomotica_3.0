package com.jose.controller.LogReg;

import com.jose.model.operations.LogRegFunctions;

public class LogRegController {
    public LogRegController(){

    }

    public void registerNewHouse(){
        String code = LogRegFunctions.registerNewHouse();
        returnRegisterHouseCode(code);
    }

    public void returnRegisterHouseCode(String code){

    }
}
