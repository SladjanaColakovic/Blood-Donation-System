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
        return new ResponseEntity<>("Korisnik nije pronađen", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BloodCenterNotFoundException.class)
    public ResponseEntity<?> bloodCenterNotFoundException(){
        return new ResponseEntity<>("Centar nije pronađen", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ChangeImageException.class)
    public ResponseEntity<?> changeImageException() {
        return new ResponseEntity<>("Neuspješna izmjena slike", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotEligibleToDonateBloodException.class)
    public ResponseEntity<?> notEligibleToDonateBloodException() {
        return new ResponseEntity<>("Nije prošao odgovarajući vremenski period od prethodnog davanja krvi." +
                " Interval između davanja krvi je 3 mjeseca za muškarce i 4 mjeseca za žene.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NoFreeAppointmentsException.class)
    public ResponseEntity<?> noFreeAppointmentsException() {
        return new ResponseEntity<>("Nema slobodnih termina za odabrani datum", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = PasswordIncorrectException.class)
    public ResponseEntity<?> passwordIncorrectException() {
        return new ResponseEntity<>("Neispravan unos stare lozinke", HttpStatus.BAD_REQUEST);
    }
}
