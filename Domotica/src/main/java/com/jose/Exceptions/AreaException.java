package com.jose.Exceptions;

public class AreaException extends Exception{
    private String message;

    public AreaException(int errorType){
        switch (errorType){
            case 1:
                message = "Relacion exitente"; break;
        }
    }

    public String getMessage(){
        return message;
    }
}
