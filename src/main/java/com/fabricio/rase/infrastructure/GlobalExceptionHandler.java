package com.fabricio.rase.infrastructure;

import com.fabricio.rase.infrastructure.persistence.SimulationRunNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SimulationRunNotFoundException.class)
    public ResponseEntity<String> handleSimulationRunNotFound(SimulationRunNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}