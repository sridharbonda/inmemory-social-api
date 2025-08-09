package com.sridhar.socialapi.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException (String message) {
        super(message);
    }
}
