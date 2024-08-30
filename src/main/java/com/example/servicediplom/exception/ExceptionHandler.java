package com.example.servicediplom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final NotFoundException e) {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final StateException e) {
        return new ResponseEntity<>(
                HttpStatus.CONFLICT
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final LimitException e) {
        return new ResponseEntity<>(
                HttpStatus.CONFLICT
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final DuplicateRequestException e) {
        return new ResponseEntity<>(
                HttpStatus.CONFLICT
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final CreatingEventBeforeTwoHoursException e) {
        return new ResponseEntity<>(
                HttpStatus.CONFLICT
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final InitiatorException e) {
        return new ResponseEntity<>(
                HttpStatus.CONFLICT
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final UnpublishedEventException e) {
        return new ResponseEntity<>(
                HttpStatus.CONFLICT
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final ItemOwnerDifferenceException e) {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }
}

