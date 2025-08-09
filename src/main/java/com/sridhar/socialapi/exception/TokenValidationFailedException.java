package com.sridhar.socialapi.exception;

public class TokenValidationFailedException extends RuntimeException{
    public TokenValidationFailedException(String message) {
        super(message);
    }
}
