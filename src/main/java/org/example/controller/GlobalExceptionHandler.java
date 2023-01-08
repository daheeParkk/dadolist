package org.example.controller;

import org.example.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DoesNotExistException.class)
    public ResponseEntity<String> DoesNotExistException(DoesNotExistException e) {

        return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DifferentPasswordException.class)
    public ResponseEntity<String> DifferentPasswordException(DifferentPasswordException e) {

        return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<String> DuplicateException(DuplicateException e) {

        return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<String> UnauthorizedException(UnauthorizedException e) {

        return new ResponseEntity<>(e.toString(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UnprivilegedAPIException.class)
    public ResponseEntity<String> UnprivilegedAPIException(UnprivilegedAPIException e) {

        return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
    }
}
