package com.epam.esm.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ModuleExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<ModuleErrorResponse> handleException(ModuleException exception){
        return new ResponseEntity<>(new ModuleErrorResponse(exception),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<ModuleErrorResponse> handleException(NumberFormatException exception){
        return new ResponseEntity<>(new ModuleErrorResponse("Incorrect value", "40001"),
                HttpStatus.BAD_REQUEST);
    }
}
