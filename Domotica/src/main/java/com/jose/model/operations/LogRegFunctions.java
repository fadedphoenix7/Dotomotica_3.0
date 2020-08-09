package com.jose.model.operations;

import com.jose.model.Generate.HouseRegistrationCode;
import com.jose.model.crud.HouseCodeCRUD;
import com.jose.model.schemas.HouseCode;

public class LogRegFunctions {
    public static String registerNewHouse(){

        String code = HouseRegistrationCode.generate(20); // Generate a house register CODE

        while(HouseCodeCRUD.exitsCode(code)){
            code = HouseRegistrationCode.generate(20); //checks if that code not exits
        }

        HouseCodeCRUD.create(code);

        return code;
    }
}
