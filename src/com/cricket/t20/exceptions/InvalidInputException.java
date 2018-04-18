package com.cricket.t20.exceptions;

public class InvalidInputException extends Exception{

    private String errorMessage;
    
    public InvalidInputException(String errorMessage){
        this.errorMessage=errorMessage;
    }
    
    @Override
    public String getMessage(){
        return errorMessage;
    }
}
