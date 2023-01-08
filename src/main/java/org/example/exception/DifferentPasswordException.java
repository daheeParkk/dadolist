package org.example.exception;

public class DifferentPasswordException extends RuntimeException {

    private final String errMessage;

    public DifferentPasswordException(String errMessage) {

        this.errMessage = errMessage;
    }

    @Override
    public String toString() {

        return "DifferentPasswordException" + "-" + errMessage;
    }
}
