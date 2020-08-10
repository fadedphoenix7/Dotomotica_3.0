package com.jose.Exceptions;

public class DeviceException extends Exception{
    private String message;

    public DeviceException(int errorType){
        switch (errorType){
            case 1:
                message = "Relacion ya existente"; break;
        }
    }

    public String getMessage(){
        return message;
    }
}
