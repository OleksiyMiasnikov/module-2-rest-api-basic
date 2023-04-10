package com.epam.esm.controllers;

import com.epam.esm.util.ModuleErrorResponse;
import com.epam.esm.util.ModuleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ModuleController {
    @ExceptionHandler
    private ResponseEntity<ModuleErrorResponse> handleException(ModuleException exception){
        return new ResponseEntity<>(new ModuleErrorResponse(exception),
                HttpStatus.NOT_FOUND);
    }
}
