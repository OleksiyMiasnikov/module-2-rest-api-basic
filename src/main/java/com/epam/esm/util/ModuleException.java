package com.epam.esm.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ModuleException extends RuntimeException{
    private String errorMessage;
    private int errorCode;
}

