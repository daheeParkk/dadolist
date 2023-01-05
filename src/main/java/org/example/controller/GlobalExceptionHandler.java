package org.example.controller;

import org.example.exception.NotFoundException;
import org.example.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundException(NotFoundException e){

        return new ResponseEntity<String>(e.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity unauthorizedException(UnauthorizedException e) {

        return new ResponseEntity<String>(e.toString(), HttpStatus.UNAUTHORIZED);
    }
}
