package com.epam.esm.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModuleErrorResponse {
    private String errorMessage;
    private String errorCode;

    public ModuleErrorResponse(ModuleException exception){
        this.errorMessage = exception.getMessage();
        this.errorCode = exception.getErrorCode();
    }
}
