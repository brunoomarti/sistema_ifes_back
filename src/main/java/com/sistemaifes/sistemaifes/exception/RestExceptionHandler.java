package com.sistemaifes.sistemaifes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleRecordNotFoundException(RecordNotFoundException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(ItemAlreadyRegisteredException.class)
    public ResponseEntity<RestErrorMessage> handleItemAlreadyRegisteredException(ItemAlreadyRegisteredException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FOUND).body(threatResponse);
    }

    @ExceptionHandler(InvalidLengthException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidLengthExceptionException(InvalidLengthException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(threatResponse);
    }
}
