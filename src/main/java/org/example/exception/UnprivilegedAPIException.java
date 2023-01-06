package org.example.exception;

public class UnprivilegedAPIException extends RuntimeException{

    private final String errMessage;

    public UnprivilegedAPIException(String errMessage){
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "UnprivilegedAPIException" + "-" + errMessage;
    }
}
