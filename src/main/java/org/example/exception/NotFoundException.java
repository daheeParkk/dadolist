package org.example.exception;

public class NotFoundException extends RuntimeException{

    private String errMessage;

    public NotFoundException(String errMessage){
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "NotFoundException" + "[" + errMessage + "]";
    }
}
