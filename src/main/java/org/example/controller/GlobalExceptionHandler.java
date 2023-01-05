package org.example.controller;

import org.example.exception.CategoryNotFoundException;
import org.example.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity CategoryNotFoundException(CategoryNotFoundException e){

        return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity unauthorizedException(UnauthorizedException e) {

        return new ResponseEntity<String>(e.toString(), HttpStatus.UNAUTHORIZED);
    }
}
