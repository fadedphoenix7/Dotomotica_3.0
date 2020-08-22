package com.jose.model.operations;

import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.crud.HouseCRUD;
import com.jose.model.crud.HouseCodeCRUD;
import com.jose.model.crud.UserCRUD;
import com.jose.model.schemas.House;
import com.jose.model.schemas.HouseCode;
import com.jose.model.schemas.User;
import com.jose.model.schemas.UserRole;

import java.util.ArrayList;

/*
    Rename Variables and refactor functions
 */

public class LogRegFunctions {

    public static String registerNewRegistrationCode(){

        String registrationCode = HouseRegistrationCode.generate(20); // Generate a house register CODE

        while(verificateCode(registrationCode)){
            registrationCode = HouseRegistrationCode.generate(20); //checks if that code not exits
        }

        HouseCodeCRUD.create(registrationCode);

        return registrationCode;
    }

    public static boolean verificateCode(String registrationCode){
        return HouseCodeCRUD.exitsCode(registrationCode); //Check if the code exits.
    }

    public static boolean registerNewHouseCode(String code){
        boolean canCreate = false;
        boolean exits = verificateCode(code);
        if(exits){
            HouseCode codeH = HouseCodeCRUD.getCodeByCode(code);
            canCreate = HouseCRUD.exitsHouseByCodeID(codeH.getID());
        }
        else{
            System.out.println("Codigo Incorrecto");
        }

        return canCreate;
    }

    public static ArrayList<User> login(String email, String password){

        ArrayList<User> usersFromDifferentHouse;
        usersFromDifferentHouse = UserCRUD.getUserByEmailAndPassword(email, password);

        return usersFromDifferentHouse;
    }

    public static House registerUserByCode(String houseCode){
        House house = HouseCRUD.getHouseByRegisterCode(houseCode);
        return house;
    }

    public static boolean isEmailRegistered(String email, int houseID){
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
    if(numbersOfUSer == 0) role = UserRole.ADMIN;

    UserFunctions.addUser(name,lastName,userEmail,password,idHouse,role);

    }

    public static House registerNewHouse(String houseName, String registrationHouseCode){
        House house = new House();
        String codeToRegisterHouse = HouseRegistrationCode.generate(15);
        while(HouseCRUD.exitsHouseByRegisterCode(codeToRegisterHouse)){
            codeToRegisterHouse = HouseRegistrationCode.generate(15);
        }
        int idregistrationCode = HouseCodeCRUD.getCodeByCode(registrationHouseCode).getID();
        house.setName(houseName);
        house.setRegistrationCodeID(idregistrationCode);
        house.setCode(codeToRegisterHouse);
        HouseCRUD.create(house);
        return house;
    }
}
