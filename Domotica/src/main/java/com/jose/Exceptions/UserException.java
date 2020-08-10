package com.jose.Exceptions;

public class UserException extends Exception{
    private String message;

    public UserException(int errorType){
        switch (errorType){
            case 1:
                message = "Contraseña incorrecta";break;
            case 2:
                message = "Permisos incorrectos ";break;
        }
    }

    @Override
    public String getMessage(){
        return message;
    }
}
