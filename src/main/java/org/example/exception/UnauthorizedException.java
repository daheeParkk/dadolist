package org.example.exception;

public class UnauthorizedException extends RuntimeException {

    private final String errMessage;

    public UnauthorizedException(String errMessage) {

        this.errMessage = errMessage;
    }

    @Override
    public String toString() {

        return "UnauthorizedException" + "-" + errMessage;
    }
}
