package com.epam.esm.exception;

public class TagException extends Exception{
    public TagException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagException(String message) {
        super(message);
    }
}
