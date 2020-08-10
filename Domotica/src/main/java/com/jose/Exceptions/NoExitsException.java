package com.jose.Exceptions;

public class NoExitsException extends Exception {
    private String message;

    public NoExitsException(Class _class){
        super();
        switch (_class.getName()){
            case "com.jose.model.schemas.User":
                message = "Correo no existente "; break;
            case "com.jose.model.schemas.House":
                message = "Codigo no existente"; break;
            case "com.jose.model.schemas.Device":
                message = "Dispositivo no existente"; break;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
