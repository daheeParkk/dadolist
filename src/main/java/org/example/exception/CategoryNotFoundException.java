package org.example.exception;

public class CategoryNotFoundException extends RuntimeException{

    private String errMessage;

    public CategoryNotFoundException(String errMessage){
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "{ CategoryNotFoundException } " + "----" + errMessage + "----";
    }
}
