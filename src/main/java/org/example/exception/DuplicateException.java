package org.example.exception;

public class DuplicateException extends RuntimeException{

    private final String errMessage;

    public DuplicateException(String errMessage){
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "DuplicateIdException" + "-" + errMessage;
    }
}