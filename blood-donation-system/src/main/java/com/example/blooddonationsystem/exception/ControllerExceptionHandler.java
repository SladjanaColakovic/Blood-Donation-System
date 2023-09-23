package com.example.blooddonationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = UserEmailExistsException.class)
    public ResponseEntity<?> userEmailExistsException(){
        return new ResponseEntity<>("Korisničko ime je zauzeto", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<?> invalidDataException() {
        return new ResponseEntity<>("Neispravni podaci", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(){
        return new ResponseEntity<>("Korisnik nije pronađen", HttpStatus.BAD_REQUEST);
    }

}
