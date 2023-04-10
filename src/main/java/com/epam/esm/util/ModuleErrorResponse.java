package com.epam.esm.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleErrorResponse {
    private String errorMessage;
    private int errorCode;

    public ModuleErrorResponse(ModuleException exception){
        this.errorMessage = exception.getErrorMessage();
        this.errorCode = exception.getErrorCode();
    }
}
