package com.jose.Exceptions;

import java.io.IOException;

public class ExitsException extends Exception {
    private String message;

    public ExitsException(Class _class){
        super();
        switch (_class.getName()){
            case "com.jose.model.schemas.User":
                message = "Correo ya existente "; break;
            case "com.jose.model.schemas.House":
                message = "Codigo ya registrado"; break;
        }
    }

    @Override
    public String getMessage(){
        return message;
    }
}
