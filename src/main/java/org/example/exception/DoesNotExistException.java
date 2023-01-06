package org.example.exception;

public class DoesNotExistException extends RuntimeException{

    private final  String errMessage;

    public DoesNotExistException(String errMessage){
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "CategoryNotFoundException" + "-" + errMessage;
    }
}
