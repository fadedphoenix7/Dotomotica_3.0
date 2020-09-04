package com.jose.model.operations;

import com.jose.model.crud.HouseCRUD;
import com.jose.model.schemas.House;

public class HouseFunctions {

    public static void update(House house){
        HouseCRUD.update(house);
    }
}
