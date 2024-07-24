package com.example.payment.handler;

import com.example.payment.exceptions.EntityNotFoundException;
import com.example.payment.exceptions.InputErratoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .exception(EntityNotFoundException.class.getSimpleName())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(InputErratoException.class)
    public ResponseEntity<ErrorResponse> handleEntityInputErratoException(InputErratoException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .exception(InputErratoException.class.getSimpleName())
                        .message(e.getMessage())
                        .build());
    }
}
