package com.sridhar.socialapi.Exception;

public class TokenValidationFailed extends RuntimeException{
    public TokenValidationFailed (String message) {
        super(message);
    }
}
