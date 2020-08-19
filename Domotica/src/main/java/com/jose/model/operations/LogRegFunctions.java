package com.jose.model.operations;

import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.crud.HouseCRUD;
import com.jose.model.crud.HouseCodeCRUD;
import com.jose.model.crud.UserCRUD;
import com.jose.model.schemas.House;
import com.jose.model.schemas.HouseCode;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;

public class LogRegFunctions {
    public static String registerNewHouse(){

        String code = HouseRegistrationCode.generate(20); // Generate a house register CODE

        while(HouseCodeCRUD.exitsCode(code)){
            code = HouseRegistrationCode.generate(20); //checks if that code not exits
        }

        HouseCodeCRUD.create(code);

        return code;
    }

    public static boolean verificateCode(String code){
        return HouseCodeCRUD.exitsCode(code);
    }

    public static boolean registerNewHouseCode(String code){
        boolean canCreate = false;
        boolean exits = verificateCode(code);
        if(exits){
            HouseCode codeH = HouseCodeCRUD.getCode(code);
            canCreate = HouseCRUD.exitsHouseByCodeID(codeH.getID());
        }
        else{
            System.out.println("Codigo Incorrecto");
        }

        return canCreate;
    }

    public static User login(String email, String password){

        User userLogged = null;
        userLogged = UserCRUD.getUser(email, password);

        return userLogged;
    }

    public static House registerCodeHouse(String houseCode){
        int houseID = HouseCRUD.exitsHouseByRegisterCode(houseCode);
        House house = null;
        if(houseID != -1) {
            house = HouseCRUD.getHouse(houseID);

        }
        return house;
    }

    public static boolean emailValidation(String email, int houseID){
        boolean emailRegistered = false;

        if(UserCRUD.exitsUserByEmailAndHouseID(email,houseID)){
            emailRegistered = true;
        }

        return emailRegistered;
    }

    public static void registerNewUser(String name, String lastName, String userEmail,
                                  String password, int idHouse){
    int numbersOfUSer = UserCRUD.getNumbersOfUserFromHouse(idHouse);
        UserRole role = UserRole.USER;
    if(numbersOfUSer < 0) role = UserRole.ADMIN;

    UserFunctions.addUser(name,lastName,userEmail,password,idHouse,role);

    }
}
